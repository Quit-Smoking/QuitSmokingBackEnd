package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 로그인 엔드포인트
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "Parameter로 LoginRequest(email, password)를 보내면 해당 사용자를 db에서 찾고 사용자의 password가 맞는 지 확인 후 토큰을 생성해서 리턴한다. 만약, 해당 email을 사용하는 사용자가 없다면 RunTimeException이 일어남")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    // 회원가입 실행
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "Parameter로 LoginRequest(email, password, nickname)을 보내면 해당 정보가 db에 저장이 된다.")
    public String register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return "Saved";
    }

    @GetMapping(path="/checkPassword")
    @Operation(summary = "비밀번호 변경을 위한 현재 비밀번호 검증", description = "Parameter로 token과 사용자가 입력한 현재 비밀번호를 받아서 db에서 확인 후 맞으면 true, 틀리면 false를 리턴")
    public Boolean checkPassword(@RequestParam String token, @RequestParam String password){
        String email = jwtUtil.extractEmail(token);
        return userService.authenticate(email, password);
    }

    @PostMapping("/changePassword")
    @Operation(summary = "비밀번호 변경", description = "Parameter로 token과 새로운 비밀번호를 받아서 db에 있는 password를 변경")
    public String changePassword(@RequestParam String token, @RequestParam String rawPassword){
        return userService.changePassword(token, rawPassword);
    }


    @GetMapping(path="/all")
    @Operation(summary = "모든 사용자 찾기", description="email, password, nickname, resolution을 Iterable<User> 타입으로 리턴한다.")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String login(@RequestBody LoginRequest request) {
        // 사용자가 제공한 이메일과 비밀번호로 인증
        if (userService.authenticate(request.getEmail(), request.getPassword())) {
            // 인증 성공 시 JWT 토큰 생성
            return jwtUtil.generateToken(request.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    // 회원가입 실행
    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        User n = new User();
        n.setEmail(request.getEmail());
        n.setPassword(request.getPassword());
        n.setNickname(request.getNickname());
        n.setResolution(request.getResolution());
        userService.register(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
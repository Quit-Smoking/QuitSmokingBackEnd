package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Security.JwtUtil;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // repository에서 User를 찾고, password와 매치한다면 True를 리턴한다.
    public boolean authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public String login(LoginRequest request){
        // 사용자가 제공한 이메일과 비밀번호로 인증
        if (authenticate(request.getEmail(), request.getPassword())) {
            // 인증 성공 시 JWT 토큰 생성
            return jwtUtil.generateToken(request.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    //register
    public void register(LoginRequest request) {
        User n = new User();
        n.setEmail(request.getEmail());
        n.setPassword(request.getPassword());
        n.setNickname(request.getNickname());
        n.setResolution(request.getResolution());

        n.setPassword(passwordEncoder.encode(n.getPassword()));
        userRepository.save(n);
    }
}
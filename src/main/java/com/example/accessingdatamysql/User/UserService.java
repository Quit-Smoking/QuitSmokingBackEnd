package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Mission.MissionRepository;
import com.example.accessingdatamysql.MissonRecord.MissionRecordRepository;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordRepository;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionRecordRepository missionRecordRepository;

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
    public String register(RegisterRequest request) {
        // 만약 같은 이메일을 가진 사용자가 있다면 에러 발생.
        if(userRepository.findByEmail(request.getEmail()) != null){
            return "동일한 이메일을 가진 사용자 존재.";
        }
        else{
            User n = new User();
            n.setEmail(request.getEmail());
            n.setPassword(passwordEncoder.encode(request.getPassword()));
            n.setNickname(request.getNickname());

            userRepository.save(n);

            return "saved";
        }
    }

    public String changePassword(String token, String rawPassword){
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(user);

        return "password changed";
    }

    public String changeNickname(String token, String nickname){
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email);
        user.setNickname(nickname);

        userRepository.save(user);

        return "nickname changed to " + userRepository.findByEmail(email).getNickname();
    }

    @Transactional
    public String deleteUser(String token, String rawPassword){
        String email = jwtUtil.extractEmail(token);

        if(authenticate(email, rawPassword)){
            Integer userId = userRepository.findByEmail(email).getId();

            userStartRecordRepository.deleteAllByUserId(userId);
            userCessationRecordRepository.deleteAllByUserId(userId);
            missionRepository.deleteAllByUserId(userId);
            missionRecordRepository.deleteAllByUserId(userId);
            userRepository.deleteById(userId);

            return "deleted";
        }
        else{
            return "password does not match";
        }
    }
}
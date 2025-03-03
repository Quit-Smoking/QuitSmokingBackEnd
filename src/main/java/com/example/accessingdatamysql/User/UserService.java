package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Board.CommentRepository;
import com.example.accessingdatamysql.Board.Post;
import com.example.accessingdatamysql.Board.PostRepository;
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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 토큰으로 바로 사용자를 찾음.
    public User findByToken(String token){
        return findByEmail(jwtUtil.extractEmail(token));
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
        // 만약 같은 이메일을 가진 사용자가 있다면 메시지 출력.
        if(!isUniqueUserEmail(request.getEmail())){
            return "동일한 이메일을 가진 사용자 존재.";
        }
        else if(EmailPasswordValidator.isValidEmail(request.getEmail())){
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setNickname(request.getNickname());

            userRepository.save(user);

            return "saved";
        }
        else{
            return "형식이 잘못되었습니다.";
        }
    }

    public boolean isUniqueUserEmail(String email){
        return userRepository.findByEmail(email) == null;
    }

    public String changePassword(String token, String rawPassword){
        User user = findByToken(token);
        user.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(user);

        return "password changed";
    }

    public String changeNickname(String token, String nickname){
        User user = findByToken(token);
        user.setNickname(nickname);

        userRepository.save(user);

        return "nickname changed to " + user.getNickname();
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
            commentRepository.deleteAllByUserId(userId);
            postRepository.deleteAllByUserId(userId);
            userRepository.deleteById(userId);

            return "deleted";
        }
        else{
            return "password does not match";
        }
    }

}
package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserCessationRecordService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    public String addNewUserCessationRecord(String token, LocalDate endDate){
        try {
            UserCessationRecord n = new UserCessationRecord();

            Integer userId = userService.findByToken(token).getId();
            n.setUserId(userId);
            n.setStartDate(userStartRecordRepository.findRecordByUserId(userId).getStartDate());
            n.setEndDate(endDate);

            userCessationRecordRepository.save(n);
            return "Saved";
        }
        catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }
    public List<UserCessationRecord> getUserCessationRecord(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return userCessationRecordRepository.findAllByUserId(userId);
    }

}

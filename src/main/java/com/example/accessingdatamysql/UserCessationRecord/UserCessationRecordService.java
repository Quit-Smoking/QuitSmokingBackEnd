package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserCessationRecordService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    public String addNewUserCessationRecord(String token, LocalDate start_date, LocalDate end_date){
        try {
            UserCessationRecord n = new UserCessationRecord();

            String email = jwtUtil.extractEmail(token);
            Integer user_id = userRepository.findByEmail(email).getId();
            n.setUserId(user_id);
            n.setStartDate(start_date);
            n.setEndDate(end_date);

            userCessationRecordRepository.save(n);
            return "Saved";
        }
        catch(Exception e){
            return "Error: " + e.getMessage();

        }
    }

}

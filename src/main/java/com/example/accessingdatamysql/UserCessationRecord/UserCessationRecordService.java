package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCessationRecordService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    public String addNewUserCessationRecord(String token, LocalDate endDate){
        try {
            UserCessationRecord n = new UserCessationRecord();

            User user = userService.findByToken(token);
            n.setUser(user);
            n.setStartDate(user.getUserStartRecord().getStartDate());
            n.setEndDate(endDate);

            userCessationRecordRepository.save(n);
            return "Saved";
        }
        catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }

    public List<UserCessationRecordResponse> getUserCessationRecord(String token){
        User user = userService.findByToken(token);

        List<UserCessationRecordResponse> responses = new ArrayList<>();

        for(UserCessationRecord record : user.getUserCessationRecords()){
            UserCessationRecordResponse response = new UserCessationRecordResponse();

            response.setStart_date(record.getStartDate());
            response.setEnd_date(record.getEndDate());

            responses.add(response);
        }

        return responses;
    }

}

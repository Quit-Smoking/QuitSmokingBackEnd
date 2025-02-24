package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCessationRecordService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    public String addUserCessationRecord(String token, float savedTime, Integer savedMoney, LocalDateTime endDate){
        try {

            User user = userService.findByToken(token);

            // 만약 user가 cessation record를 가지고 있지 않다면
            UserCessationRecord record = user.getUserCessationRecord();
            if(record == null) {
                record = new UserCessationRecord();
            }

            record.setUser(user);
            record.setStartDate(user.getUserStartRecord().getStartDate());
            record.setEndDate(endDate);
            record.setSavedTime(savedTime);
            record.setSavedMoney(savedMoney);

            userCessationRecordRepository.save(record);
            return "Saved";
        }
        catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }

    public UserCessationRecordResponse getUserCessationRecord(String token){
        User user = userService.findByToken(token);

        UserCessationRecordResponse response = new UserCessationRecordResponse();

        UserCessationRecord record = user.getUserCessationRecord();

        response.setSavedMoney(record.getSavedMoney());
        response.setSavedTime(record.getSavedTime());
        response.setStart_date(record.getStartDate());
        response.setEnd_date(record.getEndDate());

        return response;
    }

    public boolean doExist(String token){
        User user = userService.findByToken(token);

        UserCessationRecord record = user.getUserCessationRecord();
        return record != null;
    }

}

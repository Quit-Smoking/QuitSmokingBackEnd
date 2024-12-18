package com.example.accessingdatamysql.UserCessationRecord;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.accessingdatamysql.Security.JwtUtil;

import java.time.LocalDate;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/user_cessation_record")// This means URL's start with /demo (after Application path)
public class UserCessationRecordController {
    @Autowired
    private UserCessationRecordService userCessationRecordService;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    @GetMapping("/findByUser")
    @Operation(summary = "특정 사용자 금연 기록 보기" , description="user_id, start_date, end_date를 리턴")
    public UserCessationRecordResponse getUserCessationRecord(@RequestParam String token){
        return userCessationRecordService.getUserCessationRecord(token);
    }

    @GetMapping("/doExist")
    @Operation(summary = "금연을 중단한적이 없는지?")
    public boolean doExist(@RequestParam String token)
    {
        return userCessationRecordService.doExist(token);
    }

    @GetMapping("/all")
    @Operation(summary = "모든 사용자의 금연 기록 보기", description = "user_id, start_date, end_date를 리턴")
    public Iterable<UserCessationRecord> getAllUserCessationRecord() {
        return userCessationRecordRepository.findAll();
    }

}
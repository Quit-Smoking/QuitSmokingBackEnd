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

    @PostMapping("/add")
    @Operation(summary="금연 기록 등록", description="Parameter로 token, end_date를 보내면 start_date를 파악해 함께 db에 저장")// Map ONLY POST Requests
    public String addNewUserCessationRecord (@RequestParam String token, @RequestParam LocalDate endDate) {
        return userCessationRecordService.addNewUserCessationRecord(token, endDate);
    }

    @GetMapping("/findByUser")
    @Operation(summary = "특정 사용자 금연 기록 보기" , description="user_id, start_date, end_date를 리턴")
    public List<UserCessationRecord> getUserCessationRecord(@RequestParam String token){
        return userCessationRecordService.getUserCessationRecord(token);
    }

    @GetMapping("/all")
    @Operation(summary = "모든 사용자의 금연 기록 보기", description = "user_id, start_date, end_date를 리턴")
    public Iterable<UserCessationRecord> getAllUserCessationRecord() {
        return userCessationRecordRepository.findAll();
    }

}
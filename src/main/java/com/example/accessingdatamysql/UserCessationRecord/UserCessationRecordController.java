package com.example.accessingdatamysql.UserCessationRecord;
import com.example.accessingdatamysql.User.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.accessingdatamysql.Security.JwtUtil;

import java.time.LocalDate;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user_cessation_record")// This means URL's start with /demo (after Application path)
public class UserCessationRecordController {
    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserCessationRecordService userCessationRecordService;

    @PostMapping(path="/add")
    @Operation(summary="사용자 금연을 중단하면 해당 금연 기록을 저장함", description="Parameter로 token, start_date를 주면, DB에 저장한다.")// Map ONLY POST Requests
    public @ResponseBody String addNewUserCessationRecord (@RequestParam String token, @RequestParam LocalDate startDate, @RequestParam String resolution) {
        return userCessationRecordService.addNewUserCessationRecord(token, startDate, resolution);
    }

    @GetMapping(path="/all")
    @Operation(summary = "모든 사용자의 금연 기록을 (실행중인 금연기록이 아닌, end_date가 있는 금연 기록)을 불러옴", description = "email, start_date, end_date를 Iterable<UserCessationRecords> 타입으로 리턴한다")
    public @ResponseBody Iterable<UserCessationRecord> getAllUserCessationRecord() {
        return userCessationRecordRepository.findAll();
    }
}
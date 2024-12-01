package com.example.accessingdatamysql.UserCessationRecord;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecord;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.accessingdatamysql.Security.JwtUtil;

import java.time.LocalDate;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user_cessation_record")// This means URL's start with /demo (after Application path)
public class UserCessationRecordController {
    @Autowired // This means to get the bean called userCessationRecordRepository
    private UserCessationRecordRepository userCessationRecordRepository;

    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    //This means to get the bean called jwtUtil
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path="/add")
    @Operation(summary="사용자 금연을 중단하면 해당 금연 기록을 저장함", description="Parameter로 token, start_date와 end_date를 보내면 해당 정보들을 db에 저장한다")// Map ONLY POST Requests
    public @ResponseBody String addNewUserCessationRecord (@RequestParam String token, @RequestParam LocalDate end_date) {
        try {
            UserCessationRecord n = new UserCessationRecord();

            String email = jwtUtil.extractEmail(token);
            Integer user_id = userRepository.findByEmail(email).getId();
            n.setUserId(user_id);
            n.setStartDate(userStartRecordRepository.findRecordByUserId(user_id).getStartDate());
            n.setEndDate(end_date);

            userCessationRecordRepository.save(n);
            return "Saved";
        }
        catch(Exception e){
            return "Error: " + e.getMessage();

        }
    }

    @GetMapping(path="/all")
    @Operation(summary = "모든 사용자의 금연 기록을 (실행중인 금연기록이 아닌, end_date가 있는 금연 기록)을 불러옴", description = "email, start_date, end_date를 Iterable<UserCessationRecords> 타입으로 리턴한다")
    public @ResponseBody Iterable<UserCessationRecord> getAllUserCessationRecord() {
        // This returns a JSON or XML with the users
        return userCessationRecordRepository.findAll();
    }
}
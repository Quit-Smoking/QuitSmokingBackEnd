package com.example.accessingdatamysql.UserCessationRecord;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.UserStartRecord.UserStartRecordRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.accessingdatamysql.Security.JwtUtil;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/add")
    @Operation(summary="금연 기록 등록", description="Parameter로 token, end_date를 보내면 start_date를 파악해 함께 db에 저장")// Map ONLY POST Requests
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

    @GetMapping("/findByUser")
    @Operation(summary = "특정 사용자 금연 기록 보기" , description="user_id, start_date, end_date를 리턴")
    public @ResponseBody List<UserCessationRecord> getUserCessationRecord(@RequestParam String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return userCessationRecordRepository.findAllByUserId(userId);
    }

    @GetMapping("/all")
    @Operation(summary = "모든 사용자의 금연 기록 보기", description = "user_id, start_date, end_date를 리턴")
    public @ResponseBody Iterable<UserCessationRecord> getAllUserCessationRecord() {
        // This returns a JSON or XML with the users
        return userCessationRecordRepository.findAll();
    }

}
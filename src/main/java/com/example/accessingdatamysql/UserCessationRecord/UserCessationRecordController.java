package com.example.accessingdatamysql.UserCessationRecord;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.accessingdatamysql.Security.JwtUtil;

import java.time.LocalDate;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user_cessation_record") // This means URL's start with /demo (after Application path)
public class UserCessationRecordController {
    @Autowired // This means to get the bean called userCessationRecordRepository
    private UserCessationRecordRepository userCessationRecordRepository;

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    //This means to get the bean called jwtUtil
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUserCessationRecord (@RequestParam String token, @RequestParam LocalDate start_date, @RequestParam LocalDate end_date) {
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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserCessationRecord> getAllUserCessationRecord() {
        // This returns a JSON or XML with the users
        return userCessationRecordRepository.findAll();
    }
}
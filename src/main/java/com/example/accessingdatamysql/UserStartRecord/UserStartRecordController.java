package com.example.accessingdatamysql.UserStartRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/motive")
public class UserStartRecordController {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMotive(@RequestHeader("Authorization") String token, @RequestParam Integer numbers_smoked, @RequestParam String motive, @RequestParam String resolution){
        try{
            UserStartRecord n = new UserStartRecord();

            String email = jwtUtil.extractEmail(token);
            Integer user_id = userRepository.findByEmail(email).getId();
            n.setUserId(user_id);
            n.setNumbersSmoked(numbers_smoked);
            n.setMotive(motive);
            n.setResolution(resolution);

            userStartRecordRepository.save(n);
            return "Saved";
        }catch(Exception e){
            return "Error " + e.getMessage();
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserStartRecord> getAllMotive(){
        return userStartRecordRepository.findAll();
    }
}

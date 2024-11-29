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
    private UserStartRecordService userStartRecordService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMotive(@RequestBody UserStartRecordRequest request){
        return userStartRecordService.addNewUserStartRecord(request);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserStartRecord> getAllMotive(){
        return userStartRecordRepository.findAll();
    }
}

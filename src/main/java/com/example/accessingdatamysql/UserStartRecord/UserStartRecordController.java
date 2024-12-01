package com.example.accessingdatamysql.UserStartRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/UserStartRecord")
public class UserStartRecordController {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserStartRecordService userStartRecordService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMotive(@RequestBody UserStartRecordRequest request){
        return userStartRecordService.addNewUserStartRecord(request);
    }

    @GetMapping(path="/findUserRecord")
    public @ResponseBody UserStartRecord getUserMotive(@RequestParam String token){
        return userStartRecordService.findUserStartRecord(token);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserStartRecord> getAllMotive(){
        return userStartRecordRepository.findAll();
    }
}

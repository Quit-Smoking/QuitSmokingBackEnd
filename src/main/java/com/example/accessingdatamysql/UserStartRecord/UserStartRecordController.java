package com.example.accessingdatamysql.UserStartRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody String addNewMotive(@RequestBody UserStartRecordRequest request){ return userStartRecordService.addNewUserStartRecord(request); }

    @GetMapping(path="/findUserStartRecord")
    public @ResponseBody UserStartRecord findUserStartRecord(@RequestParam String token){ return  userStartRecordService.findUserStartRecord(token); }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserStartRecord> getAllMotive(){
        return userStartRecordRepository.findAll();
    }
}

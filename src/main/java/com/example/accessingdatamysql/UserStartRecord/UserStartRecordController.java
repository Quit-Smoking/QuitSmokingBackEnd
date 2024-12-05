package com.example.accessingdatamysql.UserStartRecord;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/UserStartRecord")
public class UserStartRecordController {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserStartRecordService userStartRecordService;

    @PostMapping("/add")
    @Operation(summary = "금연시작", description = "Parameter로 들어온 정보를 db와 비교해 이미 금연시작된 사용자라면 db에 저장되지 않음. 금연을 먼저 중단해야지 새로 저장을 할 수 있음")
    public String addNewStartRecord(@RequestBody UserStartRecordRequest request){ return userStartRecordService.addNewUserStartRecord(request); }

    @PostMapping("/changeResolution")
    @Operation(summary = "금연각오 수정", description = "Parameter로 token하고 새로운 각오를 받아서 db에 있는 각오를 수정함")
    public String changeResolution(@RequestParam String token, @RequestParam String resolution){
        return userStartRecordService.changeResolution(token, resolution);
    }

    @GetMapping("/doExist")
    @Operation(summary = "", description = "")
    public Boolean doExist(@RequestParam String token){
        return userStartRecordService.doExist(token);
    }

    @GetMapping("/findUserStartRecord")
    @Operation(summary = "회원별 금연시작 정보 보기", description = "Parameter로 token을 받아 특정 사용자의 금연시작 정보를 불러옴")
    public UserStartRecord findUserStartRecord(@RequestParam String token){ return  userStartRecordService.findUserStartRecord(token); }

    @GetMapping("/all")
    @Operation(summary = "모든 사용자의 금연시작 정보 보기")
    public Iterable<UserStartRecord> getAllMotive(){
        return userStartRecordRepository.findAll();
    }

    @DeleteMapping("/stop")
    @Operation(summary = "현재진행중인 금연 삭제 및 금연 기록 등록", description="현재 진행중인 금연 데이터를 삭제하고 금연 기록(UserCessationRecord)으로 저장을 함")
    public String stopQuitting(@RequestParam String token){
        return userStartRecordService.stopQuitting(token, LocalDate.now());
    }

}

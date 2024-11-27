package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.MissionRepository;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.Mission.Mission;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

@Controller // This means that this class is a Controller
@RequestMapping(path="/mission_record") // This means URL's start with /demo (after Application path)
public class MissionRecordController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private MissionRecordRepository missionRecordRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MissionRecordService missionRecordService;

    @PostMapping(path="/add")
    @Operation(summary = "사용자가 미션을 완수하면 해당 미션을 db에 저장함", description = "Parameter로 token, mission, date를 받아서 db에 저장한다")// Map ONLY POST Requests
    public @ResponseBody String addMissionRecord (@RequestBody MissionRecordRequest request) {
        return missionRecordService.addMissionRecord(request);
    }

    @GetMapping(path="/all")
    @Operation(summary="모든 사용자의 완수된 mission 기록들을 불러옴", description = "id, user_id, mission_id, date을 Iterable<MissionRecord> 타입으로 리턴한다")
    public @ResponseBody Iterable<MissionRecord> getAllMissionRecords() {
        // This returns a JSON or XML with the users
        return missionRecordRepository.findAll();
    }
}
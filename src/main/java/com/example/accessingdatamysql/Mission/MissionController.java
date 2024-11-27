package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/mission") // This means URL's start with /demo (after Application path)
public class MissionController {
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionService missionService;

    @PostMapping(path="/add")
    @Operation(summary="미션 생성 후 db에 저장함", description = "Parameter로 mission request (token, mission, is_deleted, is_default, week_data)를 보내면 해당 정보들을 db에 저장함")// Map ONLY POST Requests
    public @ResponseBody String addNewMission (@RequestBody MissionRequest request){
        return missionService.addNewMission(request);
    }


    @GetMapping(path="/all")
    @Operation(summary = "모든 사용자들의 mission들을 불러옴", description = "mission, is_deleted, is_default, week_data를 Iterable<mission> 타입으로 리턴한다")
    public @ResponseBody Iterable<Mission> getAllMissions() {
        return missionRepository.findAll();
    }
}
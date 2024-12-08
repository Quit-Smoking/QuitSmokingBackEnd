package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecord;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/mission") // This means URL's start with /demo (after Application path)
public class MissionController {
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionService missionService;

    @PostMapping(path="/add")
    @Operation(summary="미션 생성 후 db에 저장함", description = "Parameter로 정보를 보내면 해당 정보들을 db에 저장함")// Map ONLY POST Requests
    public String addNewMission (@RequestBody MissionRequest request){
        return missionService.addNewMission(request);
    }

    @GetMapping(path = "/getMissions")
    @Operation(summary = "사용자의 미션들을 보여준다.", description = "삭제되지 않은 미션을 보여준다.")
    public List<MissionResponse> getMissions(@RequestParam String token){
        return missionService.getMissions(token);
    }

    @PostMapping(path = "/deleteMission")
    @Operation(summary = "사용자의 미션을 삭제한다.", description = "Db 상에서는 삭제되지 않지만, 사용자의 미션 패널에는 보이지 않게 된다.")
    public String deleteMission(@RequestParam Integer missionId){
        return missionService.deleteMission(missionId);
    }

    @GetMapping(path="/all")
    @Operation(summary = "모든 사용자들의 미션 불러옴")
    public Iterable<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @PostMapping(path="/complete")
    @Operation(summary="오늘 특정 미션을 완료하면 미션 레코드로 띄울 수 있게 보내기")
    public Boolean complete(@RequestParam String token, @RequestParam Integer id, @RequestParam LocalDate date){
        return missionService.complete(token, id, date);
    }
}
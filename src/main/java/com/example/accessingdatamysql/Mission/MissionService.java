package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecordService;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MissionService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MissionRecordService missionRecordService;


    public String addNewMission(MissionRequest request){
        Mission n = new Mission();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer userId = userRepository.findByEmail(email).getId();

        n.setUserId(userId);
        n.setMission(request.getMission());
        n.setStartDate(request.getStart_date());
        n.setIsDeleted(request.getIs_deleted());
        n.setIsDefault(request.getIs_default());
        n.setWeekData(request.getWeek_data());

        missionRepository.save(n);

        missionRecordService.generateMissionRecords(n);

        return "Saved";
    }

    public List<Mission> getMissions(String token){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return missionRepository.findAllByUserId(userId);
    }
}

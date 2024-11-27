package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecordService;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Integer user_id = userRepository.findByEmail(email).getId();

        n.setUserId(user_id);
        n.setMission(request.getMission());
        n.setStartDate(request.getStart_date());
        n.setIsDeleted(request.getIs_deleted());
        n.setIsDefault(request.getIs_default());
        n.setWeekData(request.getWeek_data());

        missionRepository.save(n);
        // MissionRecords 들 자동으로 생성.
        missionRecordService.generateMissionRecords(n);

        return "Saved";
    }
}

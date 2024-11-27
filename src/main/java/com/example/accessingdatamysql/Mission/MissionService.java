package com.example.accessingdatamysql.Mission;

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

    public String addNewMission(MissionRequest request){
        Mission n = new Mission();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer user_id = userRepository.findByEmail(email).getId();

        n.setUserId(user_id);
        n.setMission(request.getMission());
        n.setIsDeleted(request.getIsDeleted());
        n.setIsDefault(request.getIsDefault());
        n.setWeekData(request.getWeekData());

        missionRepository.save(n);


        return "Saved";
    }
}

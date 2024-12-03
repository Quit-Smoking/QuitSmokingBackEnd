package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecordService;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MissionRecordService missionRecordService;

    // 새로운 미션을 추가한다.
    public String addNewMission(MissionRequest request){
        Mission mission = new Mission();

        try{
            String email = jwtUtil.extractEmail(request.getToken());
            Integer userId = userService.findByEmail(email).getId();

            mission.setUserId(userId);
            mission.setMission(request.getMission());
            mission.setStartDate(request.getStart_date());
            mission.setIsDeleted(request.getIs_deleted());
            mission.setIsDefault(request.getIs_default());
            mission.setWeekData(request.getWeek_data());

            missionRepository.save(mission);

            // 해당 미션에 대한 records들을 생성.
            missionRecordService.generateMissionRecords(mission);
            return "Saved";

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    // 미션을 불러온다. 중단된 미션은 불러오지 않는다.
    public List<MissionResponse> getMissions(String token){
        try{
            String email = jwtUtil.extractEmail(token);
            Integer userId = userRepository.findByEmail(email).getId();
            List<Mission> missions = missionRepository.findAllByUserId(userId);

            missions.removeIf(Mission::getIsDeleted);

            List<MissionResponse> missionResponses = new ArrayList<>();
            for(Mission mission : missions){
                MissionResponse response = new MissionResponse(mission.getId(), mission.getMission(),mission.getStartDate(), mission.getIsDefault(), mission.getWeekData());
                missionResponses.add(response);
            }

            return missionResponses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 미션을 삭제한다.
    public String deleteMission(Integer missionId){

        // 해당 미션
        Optional<Mission> missionOptional = missionRepository.findById(missionId);

        if(missionOptional.isPresent()){
            Mission mission = missionOptional.get();
            mission.setIsDeleted(true);
            missionRepository.save(mission);
            return "Deleted";
        }
        else{
            throw new RuntimeException("Mission not found!");
        }
    }
}

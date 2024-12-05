package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecord;
import com.example.accessingdatamysql.MissonRecord.MissionRecordService;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MissionRecordService missionRecordService;

    // 새로운 미션을 추가한다.
    public String addNewMission(MissionRequest request){
        Mission mission = new Mission();

        try{
            User user = userService.findByToken(request.getToken());

            mission.setUser(user);
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
            return e.getMessage();
        }

    }


    // 미션을 불러온다. 중단된 미션은 불러오지 않는다.
    public List<MissionResponse> getMissions(String token){
        try{
            User user = userService.findByToken(token);
            List<Mission> missions = user.getMissions();

            missions.removeIf(Mission::getIsDeleted);

            List<MissionResponse> missionResponses = new ArrayList<>();
            for(Mission mission : missions){
                MissionResponse response = new MissionResponse(mission.getId(), mission.getMission(),mission.getStartDate(), mission.getIsDefault(), mission.getWeekData());
                missionResponses.add(response);
            }

            return missionResponses;
        } catch (Exception e) {
            throw new RuntimeException("에러 발생!");
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

            // 삭제한 날짜 뒤에 있는 record들을 삭제함. 우선 미션에 해당하는 모든 레코드를 불러옴.
            List<MissionRecord> records = mission.getMissionRecords();

            for(int i = 0; i < records.size(); i++){
                LocalDate currentDate = LocalDate.now();

                MissionRecord record = records.get(i);
                if(record.getDate().isAfter(currentDate) || record.getDate().isEqual(currentDate))
                {
                    mission.deleteRecord(record);
                    i--;
                }
            }
            missionRepository.save(mission);

            return "Deleted";
        }
        else{
            return "해당 미션이 없음.";
        }
    }
}

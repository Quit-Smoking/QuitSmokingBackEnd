package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.Mission;
import com.example.accessingdatamysql.Mission.MissionRepository;
import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MissionRecordService {

    @Autowired
    private MissionRecordRepository missionRecordRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void generateMissionRecords(Mission mission){
        generateMissionRecords(mission, mission.getStartDate());
    }

    // date로부터 미션을 업데이트.
    public void generateMissionRecords(Mission mission, LocalDate date)
    {
        User user = mission.getUser();

        List<MissionRecord> records = new ArrayList<>();

        // 이미 이 미션에 대해 생성된 레코드들.
        List<MissionRecord> presentRecords = findAllByMissionId(mission.getId());

        // 56일 이후의 데이터까지 일단 저장.
        for(int i = 0; i < 56; i++){
            LocalDate currentDate = date.plusDays(i);
            int dayOfWeek = currentDate.getDayOfWeek().getValue();

            if(mission.getWeekData().charAt(dayOfWeek - 1) == '1'){
                // 미션에 해당하는 레코드가 이미 있다면. break.
                boolean isAlreadyIn = false;
                for(MissionRecord record : presentRecords){
                    if(record.getDate().isEqual(currentDate)){
                        isAlreadyIn = true;
                        break;
                    }
                }
                if(isAlreadyIn){
                    break;
                }
                else{
                    MissionRecord record = new MissionRecord();
                    record.setDate(currentDate);
                    record.setMission(mission);
                    record.setUser(user);
                    record.setCompleted(false);
                    records.add(record);
                }

            }
        }
        missionRecordRepository.saveAll(records);
    }


    // 매월 전체 업데이트를 진행.
    public void generateAllRecordsMonthly(){
        Iterable<Mission> missions = missionRepository.findAll();

        for(Mission mission : missions){
            // 미션이 삭제되지 않았다면 레코드를 추가.
            if(!mission.getIsDeleted()){
                generateMissionRecords(mission, LocalDate.now());
            }
        }
    }

    // 유저의 전체 레코드를 리턴함.
    public List<MissionRecordsFetchResponse> fetchMissionRecords(String token){
        // 리턴값.
        List<MissionRecordsFetchResponse> responses = new ArrayList<>();

        // 유저가 가진 레코드들.
        List<MissionRecord> records = getMissionRecordsByToken(token);

        for(MissionRecord record : records){
            MissionRecordsFetchResponse response = addResponse(record);

            responses.add(response);
        }

        return responses;
    }

    // 유저의 레코드 중 date에 해당하는 값만 리턴함.
    public List<MissionRecordsFetchResponse> fetchMissionRecords(String token, LocalDate date){
        List<MissionRecordsFetchResponse> responses = new ArrayList<>();

        // 유저가 가진 레코드들.
        List<MissionRecord> records = getMissionRecordsByToken(token);

        for(MissionRecord record : records){
            if(record.getDate().isEqual(date)) {
                MissionRecordsFetchResponse response = addResponse(record);

                responses.add(response);
            }
        }

        return responses;
    }

    public MissionRecord fetchMissionRecords(String token, Integer missionId, LocalDate date){
        MissionRecord response = new MissionRecord();

        List<MissionRecord> records = getMissionRecordsByToken(token);

        for(MissionRecord record : records){

            if(record.getDate().isEqual(date) && Objects.equals(record.getMission().getId(), missionId)){
                response = record;
                break;
            }
        }

        return response;
    }


    // 토큰에 해당하는 유저의 모든 record들을 가져옴.
    private List<MissionRecord> getMissionRecordsByToken(String token){
        // email -> user_id 찾기, mission을 찾고 이를 바탕으로 데이터를 넘겨준다.
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return getMissionRecordsByUserId(userId);
    }

    // record 안에 있는 값으로 MissionRecord를 생성.
    private MissionRecordsFetchResponse addResponse(MissionRecord record){
        MissionRecordsFetchResponse response = new MissionRecordsFetchResponse();
        response.setId(record.getId());
        response.setMissionId(record.getMission().getId());
        String missionName = missionRepository.findById(record.getMission().getId())
                .map(Mission::getMission) // Mission 객체에서 Mission 이름 가져오기
                .orElseThrow(() -> new NoSuchElementException("Mission not found for ID: " + record.getMission()));
        response.setMission(missionName);
        response.setDate(record.getDate());
        response.setCompleted(record.getCompleted());

        return response;
    }

    // 특정 record를 completed 처리함.
    // token 검사 필요. StartRecord처럼.
    public String completeMissionRecord(String token, Integer missionRecordId){
        Optional<MissionRecord> optionalRecord = missionRecordRepository.findById(missionRecordId);

        if(optionalRecord.isPresent()){
            MissionRecord record = optionalRecord.get();
            record.setCompleted(true);
            missionRecordRepository.save(record);
        }
        else{
            throw new RuntimeException("Mission record를 찾을 수 없음.");
        }
        return "Saved";
    }


    public List<MissionRecord> getMissionRecordsByUserId(Integer user_id){
        return missionRecordRepository.findAllByUserId(user_id);
    };

    public List<MissionRecord> findAllByMissionId(Integer missionId){
        return missionRecordRepository.findAllByMissionId(missionId);
    }


}

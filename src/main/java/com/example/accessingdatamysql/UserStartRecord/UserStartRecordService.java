package com.example.accessingdatamysql.UserStartRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordController;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserStartRecordService {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCessationRecordRepository userCessationRecordRepository;

    @Autowired
    private UserCessationRecordController userCessationRecordController;

    @Autowired
    private JwtUtil jwtUtil;

    //한 회원이 이미 금연중인것에 확인이 되면 새로 데이터를 생성하지 않는다
    public String addNewUserStartRecord(UserStartRecordRequest request){
        UserStartRecord n = new UserStartRecord();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer userId = userRepository.findByEmail(email).getId();

        UserStartRecord user = userStartRecordRepository.findRecordByUserId(userId);

        if(user == null){
            n.setUserId(userId);
            n.setNumbersSmoked(request.getNumbersSmoked());
            n.setMotive(request.getMotive());
            n.setResolution(request.getResolution());
            n.setStartDate(request.getStartDate());
            userStartRecordRepository.save(n);

            return "Saved";
        }else{

            return "Quit-smoking data for user already exists";
        }
    }

    public String changeResolution(String token, String resolution){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();
        UserStartRecord user = userStartRecordRepository.findRecordByUserId(userId);
        user.setResolution(resolution);

        userStartRecordRepository.save(user);

        return "resolution changed";
    }
    
    public UserStartRecord findUserStartRecord(String token){

        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        return userStartRecordRepository.findRecordByUserId(userId);

    }

    //금연을 중단했을 시 금연기록으로 더해지고 현재 진행중인 금연기록이 삭제된다 --> 금연을 재시작할 수 있는 상태가 됨
    public String stopQuitting(String token, LocalDate end_date){
        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        UserStartRecord user = userStartRecordRepository.findRecordByUserId(userId);

        String saved = userCessationRecordController.addNewUserCessationRecord(token, end_date);
        userStartRecordRepository.delete(user);

        return  saved + " to UserCessationRecords and got deleted from UserStartRecords";
    }

}

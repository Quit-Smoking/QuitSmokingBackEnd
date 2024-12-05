package com.example.accessingdatamysql.UserStartRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserService;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordController;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordRepository;
import com.example.accessingdatamysql.UserCessationRecord.UserCessationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserStartRecordService {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCessationRecordService userCessationRecordService;

    //한 회원이 이미 금연중인것에 확인이 되면 새로 데이터를 생성하지 않는다
    public String addNewUserStartRecord(UserStartRecordRequest request){
        User user = userService.findByToken(request.getToken());

        UserStartRecord userRecord = user.getUserStartRecord();

        //이거 체크 필요.
        if(userRecord == null){
            userRecord = new UserStartRecord();
            userRecord.setUser(user);
            userRecord.setNumbersSmoked(request.getNumbersSmoked());
            userRecord.setMotive(request.getMotive());
            userRecord.setResolution(request.getResolution());
            userRecord.setStartDate(request.getStartDate());
            userStartRecordRepository.save(userRecord);

            return "Saved";
        }else{
            return "Quit-smoking data for userRecord already exists";
        }
    }

    public String changeResolution(String token, String resolution){

        User user = userService.findByToken(token);
        UserStartRecord userRecord = user.getUserStartRecord();
        userRecord.setResolution(resolution);

        userStartRecordRepository.save(userRecord);

        return "resolution changed";
    }

    public UserStartRecordResponse findUserStartRecord(String token){
        User user = userService.findByToken(token);

        UserStartRecord record = user.getUserStartRecord();

        UserStartRecordResponse response = new UserStartRecordResponse();

        response.setMotive(record.getMotive());
        response.setResolution(record.getResolution());
        response.setNumbersSmoked(record.getNumbersSmoked());
        response.setStartDate(record.getStartDate());

        return response;

    }

    @Transactional
    //금연을 중단했을 시 금연기록으로 더해지고 현재 진행중인 금연기록이 삭제된다 --> 금연을 재시작할 수 있는 상태가 됨
    public String stopQuitting(String token, LocalDate endDate){
        User user = userService.findByToken(token);

        UserStartRecord userRecord = user.getUserStartRecord();

        String saved = userCessationRecordService.addNewUserCessationRecord(token, endDate);

        user.setUserStartRecord(null);
        userStartRecordRepository.deleteById(userRecord.getId());

        return  saved + " to UserCessationRecords and got deleted from UserStartRecords";
    }

    public boolean doExist(String token){
        User user = userService.findByToken(token);

        return user.getUserStartRecord() != null;
    }

}

package com.example.accessingdatamysql.UserStartRecord;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStartRecordService {
    @Autowired
    private UserStartRecordRepository userStartRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

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
            user.setMotive(request.getMotive());
            user.setResolution(request.getResolution());
            user.setNumbersSmoked(request.getNumbersSmoked());
            userStartRecordRepository.save(user);

            return "Changed existing data";
        }

    }

    public UserStartRecord findUserStartRecord(String token){

        String email = jwtUtil.extractEmail(token);
        Integer userId = userRepository.findByEmail(email).getId();

        UserStartRecord user = userStartRecordRepository.findRecordByUserId(userId);
        if(user == null){
            return null;
        }else{
            return userStartRecordRepository.findRecordByUserId(userId);
        }

    }

}

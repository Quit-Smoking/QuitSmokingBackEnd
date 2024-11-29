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

    private UserStartRecord n;

    public String addNewUserStartRecord(UserStartRecordRequest request){
        n = new UserStartRecord();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer userId = userRepository.findByEmail(email).getId();
        n.setUserId(userId);
        n.setNumbersSmoked(request.getNumbersSmoked());
        n.setMotive(request.getMotive());
        n.setResolution(request.getResolution());
        n.setStartDate(request.getStartDate());

        userStartRecordRepository.save(n);

        return "Saved";

    }

    public UserStartRecord findNewUserStartRecord(Integer id){

        return userStartRecordRepository.findRecordById(id);
    }

}

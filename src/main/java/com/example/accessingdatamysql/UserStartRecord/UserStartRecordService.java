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
        Integer user_id = userRepository.findByEmail(email).getId();
        n.setUserId(user_id);
        n.setNumbersSmoked(request.getNumbersSmoked());
        n.setMotive(request.getMotive());
        n.setResolution(request.getResolution());
        n.setStartDate(request.getStartDate());

        userStartRecordRepository.save(n);
        return "Saved";
    }


}

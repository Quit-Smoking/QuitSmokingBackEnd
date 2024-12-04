package com.example.accessingdatamysql.NicotinDependencies;

import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class NicotinDependenciesService {

    @Autowired
    private UserService userService;

    @Autowired
    private NicotinDependenciesRepository nicotinDependenciesRepository;

    public String addNicotinDependencies(String token, NicotinDependenciesRequest request)
    {

        try {
            User user = userService.findByToken(token);

            // 이미 기록이 있는지?
            NicotinDependencies dependencies = findByUserId(user.getId());
            if(dependencies == null){
                dependencies = new NicotinDependencies();
            }

            dependencies.setUserId(user.getId());
            dependencies.setAfternoonOrDinnerNum(request.getAfternoonOrDinnerNum());
            dependencies.setFirstSmokeTimeNum(request.getFirstSmokeTimeNum());
            dependencies.setGreatestSmokeOnDayNum(request.getGreatestSmokeOnDayNum());
            dependencies.setHardToHoldSmokeNum(request.getHardToHoldSmokeNum());
            dependencies.setNumberSmokedPerDayNum(request.getNumberSmokedPerDayNum());
            dependencies.setYouSickSmokeNum(request.getYouSickSmokeNum());

            nicotinDependenciesRepository.save(dependencies);

            return "Saved";
        }
        catch (Exception e){
            return "User not found.";
        }
    }

    public NicotinDependencies findByUserId(Integer userId){
        return nicotinDependenciesRepository.findByUserId(userId);
    }
}

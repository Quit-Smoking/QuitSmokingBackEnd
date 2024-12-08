package com.example.accessingdatamysql.NicotinDependencies;

import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            NicotinDependencies dependencies = user.getNicotinDependencies();

            if(dependencies == null){
                dependencies = new NicotinDependencies();
            }

            dependencies.setUser(user);
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

    public Integer getNicotinDependenciesScore(String token){
        try{
            NicotinDependencies dependencies = userService.findByToken(token).getNicotinDependencies();

            return calScore(dependencies);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // dependency에 있는 값들을 토대로 점수를 계산.
    private Integer calScore(NicotinDependencies dependencies)
    {
        int score = 0;
        //1. 아침에 일어나서 몇개비를 피는지.
        switch (dependencies.getFirstSmokeTimeNum())
        {
            case 1:
                score += 0;
                break;
            case 2:
                score += 1;
                break;
            case 3:
                score += 2;
                break;
            case 4:
                score += 3;
                break;
        }
        // 2. 금연구역에서 담배를 참기가 어려운지.
        if (dependencies.getHardToHoldSmokeNum() == 1) {
            score += 1;
        }

        // 3. 하루에서 담배맛이 가장 좋을 때는 언제인지
        if(dependencies.getGreatestSmokeOnDayNum() == 1){
            score += 1;
        }

        // 4. 하루에 몇개비나 피우십니까?
        switch (dependencies.getNumberSmokedPerDayNum()){
            case 1:
                score += 0;
                break;
            case 2:
                score += 1;
                break;
            case 3:
                score += 2;
                break;
            case 4:
                score += 3;
                break;
        }

        // 5. 오후와 저녁 시간보다 오전 중에 담배를 더 자주 피우십니까?
        if(dependencies.getAfternoonOrDinnerNum() == 1){
            score += 1;
        }

        // 6. 몸이 아파 하루종일 누워 있을 때에도 담배를 피우십니까?
        if(dependencies.getYouSickSmokeNum() == 1){
            score += 1;
        }

        return score;
    }

    public boolean isTested(String token) {
        User user = userService.findByToken(token);

        if (user == null || user.getId() == null) {
            return false;
        }

        return nicotinDependenciesRepository.findByUserId(user.getId()) != null;
    }
}

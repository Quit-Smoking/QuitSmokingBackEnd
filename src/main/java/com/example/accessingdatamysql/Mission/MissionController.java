package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/mission") // This means URL's start with /demo (after Application path)
public class MissionController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private MissionRepository missionRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewMission (@RequestBody MissionRequest request){
        Mission n = new Mission();

        String email = jwtUtil.extractEmail(request.getToken());
        Integer user_id = userRepository.findByEmail(email).getId();

        n.setUserId(user_id);
        n.setMission(request.getMission());
        n.setIsDeleted(request.getIsDeleted());
        n.setIsDefault(request.getIsDefault());
        n.setWeekData(request.getWeekData());

        missionRepository.save(n);
        return "Saved";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Mission> getAllMissions() {
        // This returns a JSON or XML with the users
        return missionRepository.findAll();
    }
}
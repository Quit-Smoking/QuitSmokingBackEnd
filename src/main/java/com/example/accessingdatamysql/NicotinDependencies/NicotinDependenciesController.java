package com.example.accessingdatamysql.NicotinDependencies;

import com.example.accessingdatamysql.User.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nicotin_dependencies")
public class NicotinDependenciesController {

    @Autowired
    private NicotinDependenciesService nicotinDependenciesService;

    @Autowired
    private NicotinDependenciesRepository nicotinDependenciesRepository;

    @PostMapping("/add")
    @Operation(summary = "니코틴 기록을 저장한다.", description = "니코틴 기록을 저장할 때 이미 기록이 존재한다면 원래의 기록을 수정한다.")
    public @ResponseBody String addNicotinDependencies(@RequestParam String token, @RequestBody NicotinDependenciesRequest request)
    {
        return nicotinDependenciesService.addNicotinDependencies(token, request);
    }

//    @GetMapping("/get")
//    public @ResponseBody NicotinDependenciesRequest getNicotinDependencies(@RequestParam String token)
//    {
//
//    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<NicotinDependencies> getAll(){
        return nicotinDependenciesRepository.findAll();
    }

}

package com.example.accessingdatamysql.Security;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class JwtUtilTest {
    @Autowired
    JwtUtil jwtUtil = new JwtUtil();

    @GetMapping("/generateToken")
    @Operation(summary="쓰지 않는 함수", description = "필요 없음")
    public String generateTokenTest(@RequestParam String email){
        try{
            return jwtUtil.generateToken(email);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}

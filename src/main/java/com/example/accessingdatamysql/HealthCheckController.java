package com.example.accessingdatamysql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/healthy")
    public String healthCheck() {
        return "OK";
    }
}

package com.example.accessingdatamysql.CoupangCrawling;

import io.swagger.v3.core.util.Json;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/coupang")
public class CoupangCrawlingController {
    @GetMapping(path = "search")
    public String searchProduct(@RequestBody SearchRequest request){



        return "ok.";
    }
}

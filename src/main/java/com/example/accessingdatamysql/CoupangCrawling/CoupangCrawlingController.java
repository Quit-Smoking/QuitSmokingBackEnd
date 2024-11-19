package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import com.example.accessingdatamysql.CoupangCrawling.CoupangCrawlingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/coupang")
public class CoupangCrawlingController {

    private final CoupangCrawlingService coupangCrawlingService;

    public CoupangCrawlingController(CoupangCrawlingService coupangCrawlingService) {
        this.coupangCrawlingService = coupangCrawlingService;
    }

    @GetMapping(path = "/search")
    public SearchAnswer searchProducts(@RequestParam String keyword, @RequestParam Integer page) {
        return coupangCrawlingService.searchProducts(keyword, page);
    }
}

package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import com.example.accessingdatamysql.CoupangCrawling.CoupangCrawlingService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    @GetMapping(path = "test")
    public String testSearch(){
        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        // ChromeOptions 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // GUI 없이 실행
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // WebDriver 초기화
        WebDriver driver = new ChromeDriver(options);

        // 테스트 URL 열기
        driver.get("https://www.google.com");

        // 페이지 제목 확인
        String answer = "Page title is: " + driver.getTitle();

        answer += driver.toString();

        // 드라이버 종료
        driver.quit();

        return answer;
    }
}

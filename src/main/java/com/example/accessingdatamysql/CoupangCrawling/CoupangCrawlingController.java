package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import com.example.accessingdatamysql.CoupangCrawling.CoupangCrawlingService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

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
        //System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.coupang.com");

            // 요소가 나타날 때까지 대기 (최대 10초)
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-title")));

            //String answer = element.getText();
            return driver.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if(driver != null) driver.quit();
        }
    }
}

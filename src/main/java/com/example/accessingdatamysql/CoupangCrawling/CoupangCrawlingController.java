package com.example.accessingdatamysql.CoupangCrawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/coupang")
public class CoupangCrawlingController {
    @GetMapping(path = "/search")
    public SearchAnswer searchProducts(@RequestParam String keyword, @RequestParam Integer page){
        WebDriver driver = new ChromeDriver();

        SearchAnswer searchAnswer = new SearchAnswer();
        String url = "https://www.coupang.com/np/search?q=" + keyword + "&page=" + page;
        try {
            driver.get(url);

            List<WebElement> elements = driver.findElements(By.className("search-product"));
            // element는 하나의 상품.
            for(WebElement element : elements){
                String productName = element.findElement(By.className("name")).getText();
                String productPrice = element.findElement(By.className("price-value")).getText();
                String productImageUrl = element.findElement(By.className("search-product-wrap-img")).getAttribute("src");
                String productUrl = element.findElement(By.className("search-product-link")).getAttribute("href");
                float rank = Float.parseFloat(element.findElement(By.className("rating")).getText());

                ProductData product = new ProductData(productName, productPrice, productImageUrl, productUrl, rank);
                searchAnswer.addProduct(product);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return searchAnswer;
    }

    @Async
    @GetMapping("/test")
    public void test() {
    }
}

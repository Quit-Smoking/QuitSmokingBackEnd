package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.CoupangCrawling.ProductData;
import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoupangCrawlingService {

    public SearchAnswer searchProducts(String keyword, Integer page) {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");


        WebDriver driver = new ChromeDriver(options);
        SearchAnswer searchAnswer = new SearchAnswer();
        String url = "https://www.coupang.com/np/search?q=" + keyword + "&page=" + page;

        try {
            driver.get(url);

            List<WebElement> elements = driver.findElements(By.className("search-product"));
            for (WebElement element : elements) {
                String productName = getElementText(element, By.className("name"));
                String productPrice = getElementText(element, By.className("price-value"));
                String productImageUrl = getElementAttribute(element, By.className("search-product-wrap-img"), "src");
                String productUrl = getElementAttribute(element, By.className("search-product-link"), "href");
                float rank = getElementTextAsFloat(element, By.className("rating"), 0.0f);

                ProductData product = new ProductData(productName, productPrice, productImageUrl, productUrl, rank);
                searchAnswer.addProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return searchAnswer;
    }

    private String getElementText(WebElement element, By by) {
        List<WebElement> elements = element.findElements(by);
        return elements.isEmpty() ? null : elements.get(0).getText();
    }

    private String getElementAttribute(WebElement element, By by, String attribute) {
        List<WebElement> elements = element.findElements(by);
        return elements.isEmpty() ? null : elements.get(0).getAttribute(attribute);
    }

    private float getElementTextAsFloat(WebElement element, By by, float defaultValue) {
        List<WebElement> elements = element.findElements(by);
        if (!elements.isEmpty()) {
            try {
                return Float.parseFloat(elements.get(0).getText());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}

package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.CoupangCrawling.ProductData;
import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CoupangCrawlingService {

    public SearchAnswer searchProducts(String keyword, Integer page) {

        //System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        //options.addArguments("--no-sandbox");


        WebDriver driver = null;
        SearchAnswer searchAnswer = new SearchAnswer();
        String url = "https://www.coupang.com/np/search?q=" + keyword + "&page=" + page;

        try {
            driver = new ChromeDriver(options);
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("search-product")));

            List<WebElement> elements = driver.findElements(By.className("search-product"));
            for (WebElement element : elements) {
                String productName = getElementText(element, By.className("name"), wait);
                String productPrice = getElementText(element, By.className("price-value"), wait);
                String productImageUrl = getElementAttribute(element, By.className("search-product-wrap-img"), "src", wait);
                String productUrl = getElementAttribute(element, By.className("search-product-link"), "href", wait);
                float rank = getElementTextAsFloat(element, By.className("rating"), 0.0f, wait);

                ProductData product = new ProductData(productName, productPrice, productImageUrl, productUrl, rank);
                searchAnswer.addProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null)
                driver.quit();
        }

        return searchAnswer;
    }

    private String getElementText(WebElement element, By by, WebDriverWait wait) {
        try {
            WebElement targetElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by));
            return targetElement.getText();
        } catch (Exception e) {
            return null;
        }

    }

    private String getElementAttribute(WebElement element, By by, String attribute, WebDriverWait wait) {
        try {
            WebElement targetElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by));
            return targetElement.getAttribute(attribute);
        } catch (Exception e) {
            return null;
        }
    }

    private float getElementTextAsFloat(WebElement element, By by, float defaultValue, WebDriverWait wait) {
        try {
            WebElement targetElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by));
            return Float.parseFloat(targetElement.getText());
        } catch (Exception e) {
            return defaultValue;
        }
    }
}

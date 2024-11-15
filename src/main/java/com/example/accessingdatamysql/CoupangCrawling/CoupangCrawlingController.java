package com.example.accessingdatamysql.CoupangCrawling;

import com.example.accessingdatamysql.User.LoginRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/coupang")
public class CoupangCrawlingController {
    @GetMapping(path = "/search")
    public SearchAnswer searchProducts(@RequestParam String keyword, @RequestParam Integer page){
        SearchAnswer searchAnswer = new SearchAnswer();
        try {
            String coupangUrl = "https://www.coupang.com/np/search?component=&q=" + keyword + "&page=" + page;
            Document doc = Jsoup.connect(coupangUrl).get();
            Elements elements = doc.select("search-product-link");

            for (Element element : elements){
                String productName = element.select("name").text();
                String productPrice = element.select("base-price").text();
                String productImageUrl = element.select("search-product-wrap-img").attr("src");
                String productUrl = element.attr("href");
                float rank = Float.parseFloat(element.select("rating").text());

                ProductData product = new ProductData(productName, productPrice, productImageUrl, productUrl, rank);

                searchAnswer.addProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchAnswer;
    }

    @GetMapping("/test")
    public String test(@RequestParam String keyword, @RequestParam Integer page) {
        SearchAnswer searchAnswer = new SearchAnswer();
        try {
            String coupangUrl = "https://www.naver.com/";
            Document doc = Jsoup.connect(coupangUrl).get();

            //Elements elements = doc.select("search-product-link");
            return doc.toString();
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

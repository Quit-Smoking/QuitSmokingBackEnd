package com.example.accessingdatamysql.CoupangCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    public String test() {
        SearchAnswer searchAnswer = new SearchAnswer();
        try {
            String coupangUrl = "https://www.coupang.com/?src=1042016&spec=10304902&addtag=900&ctag=HOME&lptag=coupang&itime=20241119125644&pageType=HOME&pageValue=HOME&wPcid=17063312576687348609914&wRef=www.google.com&wTime=20241119125644&redirect=landing&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrATv7TtFW3-TX40WLMiVlbWDF8n6MmQhQpSrW9XGbcbIbZTaH_qqHQaAlTYEALw_wcB&mcid=c01144e02ba24009859858c594192d76&network=g";
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

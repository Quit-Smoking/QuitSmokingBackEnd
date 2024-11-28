package com.example.accessingdatamysql.Store;

import com.example.accessingdatamysql.CoupangCrawling.ProductData;
import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    StoreProductRepository storeProductRepository;

    // keywordId를 받아와서 그에 맞게
    public String saveProducts(SearchAnswer request, Integer keywordId){
        // storeProducts 형식.
        List<StoreProduct> storeProducts = new ArrayList<>();

        // product들의 데이터만 받아온다. 하나씩 StoreProducts에 넣기 시작한다.
        List<ProductData> products = request.getProductData();
        for(ProductData product : products){
            StoreProduct storeProduct = new StoreProduct();

            storeProduct.setKeyWordId(keywordId);
            storeProduct.setProductName(product.getProductName());
            storeProduct.setProductPrice(product.getProductPrice());
            storeProduct.setProductUrl(product.getProductUrl());
            storeProduct.setProductImageUrl(product.getProductImageUrl());
            storeProduct.setRanking(product.getRank());

            storeProducts.add(storeProduct);
        }
        // 전부 끝나면 repository에 saveall.

        storeProductRepository.saveAll(storeProducts);

        return "Saved";
    }
}

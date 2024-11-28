package com.example.accessingdatamysql.Store;

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

            storeProduct.setKeywordId(keywordId);
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


    // 사용자에게 해당 keyword에 해당하는 모든 상품들을 보여줌.
    public SearchAnswer showProducts(Integer keyWordId){
        SearchAnswer searchAnswer = new SearchAnswer();

        // 현재 키워드가 포함된 모든 상품을 들여온다.
        List<StoreProduct> storeProducts = findProductsByKeywordId(keyWordId);

        for(StoreProduct product : storeProducts){
            ProductData productData = new ProductData(product.getProductName(), product.getProductPrice(), product.getProductImageUrl(), product.getProductUrl(), product.getRanking());
            searchAnswer.addProduct(productData);
        }
        return searchAnswer;
    }

    public List<StoreProduct> findProductsByKeywordId(Integer keyWordId){
        return storeProductRepository.findByKeywordId(keyWordId);
    }
}

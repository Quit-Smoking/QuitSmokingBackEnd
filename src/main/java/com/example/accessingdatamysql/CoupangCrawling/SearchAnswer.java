package com.example.accessingdatamysql.CoupangCrawling;

import java.util.List;

public class SearchAnswer {
    private List<ProductData> productData;

    public void addProduct(ProductData product) {
        productData.add(product);
    }
}

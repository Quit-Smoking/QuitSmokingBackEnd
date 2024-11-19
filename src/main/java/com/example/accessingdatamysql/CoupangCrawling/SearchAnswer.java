package com.example.accessingdatamysql.CoupangCrawling;

import java.util.ArrayList;
import java.util.List;

public class SearchAnswer {


    private List<ProductData> productData = new ArrayList<>();

    public void addProduct(ProductData product) {
        productData.add(product);
    }
    public List<ProductData> getProductData() {
        return productData;
    }
    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }

}

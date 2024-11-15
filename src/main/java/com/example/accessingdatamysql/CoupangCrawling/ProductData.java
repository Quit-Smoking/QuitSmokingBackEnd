package com.example.accessingdatamysql.CoupangCrawling;

public class ProductData {
    private String productName;
    private String productPrice;
    private String productImageUrl;
    private String productUrl;
    private float rank;

    public ProductData(String productName, String productPrice, String productImageUrl, String productUrl, float rank){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.productUrl = productUrl;
        this.rank = rank;
    }
}

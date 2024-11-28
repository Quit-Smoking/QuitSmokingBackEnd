package com.example.accessingdatamysql.Store;

import jakarta.persistence.*;

//0 니코틴 솔루션 / 1 니코틴 배출 영양제
//2 니코틴 검사키트
//3 니코틴 제거제
// 5 금연패치
//6 금연껌
//7 폐건강 영양제
//8 니코틴 제거 치약

@Entity
@Table(name = "store_products")
public class StoreProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keyword_id")
    private Integer keyWordId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "product_image_url", length = 2048)
    private String productImageUrl;

    @Column(name = "product_url", length = 2048)
    private String productUrl;

    private float ranking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeyWordId() {
        return keyWordId;
    }

    public void setKeyWordId(Integer keyWordId) {
        this.keyWordId = keyWordId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

}

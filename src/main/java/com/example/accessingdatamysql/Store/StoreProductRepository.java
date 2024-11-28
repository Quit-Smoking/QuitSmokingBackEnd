package com.example.accessingdatamysql.Store;

import org.apache.catalina.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StoreProductRepository extends CrudRepository<StoreProduct, Integer> {
    List<StoreProduct> findByKeywordId(Integer keywordId);
}
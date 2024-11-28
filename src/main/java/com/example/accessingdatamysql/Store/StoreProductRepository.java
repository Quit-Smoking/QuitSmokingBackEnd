package com.example.accessingdatamysql.Store;

import org.apache.catalina.Store;
import org.springframework.data.repository.CrudRepository;


public interface StoreProductRepository extends CrudRepository<StoreProduct, Integer> {
}
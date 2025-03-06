package com.example.accessingdatamysql.Item;


import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository<Item, Integer> {

}
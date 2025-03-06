package com.example.accessingdatamysql.Item;


import org.springframework.data.repository.CrudRepository;


public interface UserItemRepository extends CrudRepository<UserItem, Integer> {

}
package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MissionRepository extends CrudRepository<Mission, Integer> {
    void deleteAllByUserid(Integer userId);
}
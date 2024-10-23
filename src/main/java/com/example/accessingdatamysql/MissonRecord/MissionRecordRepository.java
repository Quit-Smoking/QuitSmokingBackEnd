package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.Mission;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MissionRecordRepository extends CrudRepository<MissionRecord, Integer> {
}
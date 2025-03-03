package com.example.accessingdatamysql.MissonRecord;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MissionRecordRepository extends CrudRepository<MissionRecord, Integer> {
    List<MissionRecord> findAllByUserId(Integer userId);
    void deleteAllByUserId(Integer userId);
    List<MissionRecord> findAllByMissionId(Integer missionId);
}
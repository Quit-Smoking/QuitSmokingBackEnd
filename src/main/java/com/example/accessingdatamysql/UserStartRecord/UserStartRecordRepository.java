package com.example.accessingdatamysql.UserStartRecord;

import org.springframework.data.repository.CrudRepository;

public interface UserStartRecordRepository extends CrudRepository<UserStartRecord, Integer> {
    UserStartRecord findRecordByUserId(Integer userId);
    void deleteAllByUserId(Integer userId);

}

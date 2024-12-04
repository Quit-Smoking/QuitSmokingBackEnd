package com.example.accessingdatamysql.MissonRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonthlyTask {

    @Autowired
    private MissionRecordService missionRecordService;

    // 매월 Record를 업데이트.
    @Scheduled(cron = "0 0 0 1 * ?")
    public void executeMonthlyTask(){
        System.out.println("record 전체 업데이트.");
        // 삭제되지 않은 모든 미션에 대해서 Record 업데이트.
        missionRecordService.generateAllRecordsMonthly();
    }


}

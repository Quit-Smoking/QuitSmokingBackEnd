package com.example.accessingdatamysql.MissonRecord;

import java.time.LocalDate;

public class MissionRecordRequest {

    private Integer userId;
    private Integer missionId;
    private LocalDate date;

    public Integer getUserId(){return userId;}
    public Integer getMissionId(){return missionId;}
    public LocalDate getDate(){
        return date;
    }
}
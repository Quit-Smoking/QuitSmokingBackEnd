package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.Mission;

import java.time.LocalDate;

public class MissionRecordRequest {
    private String token;
    private Mission mission;
    private LocalDate date;

    public String getToken(){
        return token;
    }

    public Mission getMission(){
        return mission;
    }

    public LocalDate getDate(){
        return date;
    }

}
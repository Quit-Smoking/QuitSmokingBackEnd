package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.Mission;

import java.time.LocalDate;

public class MissionRecordRequest {

    private Integer user_id;
    private Integer mission_id;
    private LocalDate date;

    public Integer getUser_id(){return user_id;}
    public Integer getMission_id(){return mission_id;}
    public LocalDate getDate(){
        return date;
    }
}
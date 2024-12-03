package com.example.accessingdatamysql.Mission;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class MissionResponse {
    private Integer id;
    private String mission;
    private LocalDate startDate;
    private boolean isDefault;
    private String weekData;


    public MissionResponse(Integer id, String mission, LocalDate startDate,
                           boolean isDefault, String weekData){
        this.id = id;
        this.mission = mission;
        this.startDate = startDate;
        this.isDefault = isDefault;
        this.weekData = weekData;
    }

    public String getWeekData() {
        return weekData;
    }

    public void setWeekData(String weekData) {
        this.weekData = weekData;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
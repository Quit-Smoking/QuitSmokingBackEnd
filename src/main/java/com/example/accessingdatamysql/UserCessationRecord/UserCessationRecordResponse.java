package com.example.accessingdatamysql.UserCessationRecord;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserCessationRecordResponse {

    private Integer savedTime;

    private Integer savedMoney;

    private LocalDate start_date;

    private LocalDate end_date;

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public Integer getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(Integer savedTime) {
        this.savedTime = savedTime;
    }

    public Integer getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Integer savedMoney) {
        this.savedMoney = savedMoney;
    }
}

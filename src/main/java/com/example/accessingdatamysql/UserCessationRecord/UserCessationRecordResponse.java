package com.example.accessingdatamysql.UserCessationRecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class UserCessationRecordResponse {

    private float savedTime;

    private Integer savedMoney;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_date;

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public float getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(float savedTime) {
        this.savedTime = savedTime;
    }

    public Integer getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Integer savedMoney) {
        this.savedMoney = savedMoney;
    }
}

package com.example.accessingdatamysql.UserCessationRecord;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserCessationRecordResponse {
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
}

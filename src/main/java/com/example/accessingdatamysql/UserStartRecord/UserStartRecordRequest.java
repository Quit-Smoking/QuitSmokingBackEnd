package com.example.accessingdatamysql.UserStartRecord;

import java.time.LocalDate;

public class UserStartRecordRequest {

    private String token;
    private Integer numbers_smoked;
    private String resolution;
    private String motive;
    private LocalDate start_date;

    public String getToken() {
        return token;
    }

    public Integer getNumbersSmoked() {
        return numbers_smoked;
    }

    public String getResolution() {
        return resolution;
    }

    public String getMotive() {
        return motive;
    }

    public LocalDate getStartDate() {
        return start_date;
    }
}

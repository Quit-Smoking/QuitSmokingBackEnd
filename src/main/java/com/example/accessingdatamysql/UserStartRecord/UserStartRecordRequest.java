package com.example.accessingdatamysql.UserStartRecord;

import java.time.LocalDate;

public class UserStartRecordRequest {

    private String token;
    private String resolution;
    private String motive;
    private LocalDate startDate;
    private Integer numbersSmoked;

    public String getToken() {
        return token;
    }

    public Integer getNumbersSmoked() {
        return numbersSmoked;
    }

    public String getResolution() {
        return resolution;
    }

    public String getMotive() {
        return motive;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}

package com.example.accessingdatamysql.UserStartRecord;

import java.time.LocalDateTime;

public class UserStartRecordRequest {

    private String token;
    private String resolution;
    private String motive;
    private LocalDateTime startDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }
}

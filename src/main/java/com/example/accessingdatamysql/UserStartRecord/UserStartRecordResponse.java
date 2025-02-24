package com.example.accessingdatamysql.UserStartRecord;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UserStartRecordResponse {

    private String motive;

    private String resolution;

    private Integer numbersSmoked;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getNumbersSmoked() {
        return numbersSmoked;
    }

    public void setNumbersSmoked(Integer numbersSmoked) {
        this.numbersSmoked = numbersSmoked;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

}

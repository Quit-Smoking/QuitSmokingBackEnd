package com.example.accessingdatamysql.UserStartRecord;

import java.time.LocalDate;

public class UserStartRecordResponse {

    private String motive;

    private String resolution;

    private Integer numbersSmoked;

    private LocalDate startDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}

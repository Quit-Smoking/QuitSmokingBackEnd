package com.example.accessingdatamysql.UserStartRecord;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="UserStartRecord")
public class UserStartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String motive;

    private String resolution;

    private Integer numbersSmoked;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
    public LocalDate getStartDate(){
        return startDate;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
}

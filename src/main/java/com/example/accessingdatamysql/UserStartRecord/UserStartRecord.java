package com.example.accessingdatamysql.UserStartRecord;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Motive")
public class UserStartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;

    private String motive;

    private String resolution;

    private Integer numbers_smoked;

    private LocalDate start_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
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
        return numbers_smoked;
    }

    public void setNumbersSmoked(Integer numbers_smoked) {
        this.numbers_smoked = numbers_smoked;
    }
    public LocalDate getStartDate(){
        return start_date;
    }
    public void setStartDate(LocalDate start_date){
        this.start_date = start_date;
    }
}

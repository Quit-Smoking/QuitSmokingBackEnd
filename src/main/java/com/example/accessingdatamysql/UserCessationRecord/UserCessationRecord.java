package com.example.accessingdatamysql.UserCessationRecord;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="user_cessation_records")
public class UserCessationRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDate end_date) {
        this.end_date = end_date;
    }
}
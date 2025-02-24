package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="user_cessation_records")
public class UserCessationRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private float savedTime;

    private Integer savedMoney;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDateTime end_date) {
        this.end_date = end_date;
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
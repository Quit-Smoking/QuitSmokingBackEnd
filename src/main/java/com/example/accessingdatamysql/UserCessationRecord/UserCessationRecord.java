package com.example.accessingdatamysql.UserCessationRecord;

import com.example.accessingdatamysql.User.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="user_cessation_records")
public class UserCessationRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer savedTime;

    private Integer savedMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

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

    public Integer getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(Integer savedTime) {
        this.savedTime = savedTime;
    }

    public Integer getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(Integer savedMoney) {
        this.savedMoney = savedMoney;
    }
}
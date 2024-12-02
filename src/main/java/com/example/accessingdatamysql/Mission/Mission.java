package com.example.accessingdatamysql.Mission;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="missions")
public class Mission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String mission;

    private LocalDate start_date;

    private boolean is_deleted;

    private boolean is_default;

    private String week_data;

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

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public boolean isIsDeleted() {
        return is_deleted;
    }

    public void setIsDeleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean isIsDefault() {
        return is_default;
    }

    public void setIsDefault(boolean is_default) {
        this.is_default = is_default;
    }

    public String getWeekData() {
        return week_data;
    }

    public void setWeekData(String week_data) {
        this.week_data = week_data;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }

}
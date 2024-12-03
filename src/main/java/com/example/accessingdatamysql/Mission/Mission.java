package com.example.accessingdatamysql.Mission;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="missions")
public class Mission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String mission;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "week_data")
    private String weekData;

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

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean is_deleted) {
        this.isDeleted = is_deleted;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean is_default) {
        this.isDefault = is_default;
    }

    public String getWeekData() {
        return weekData;
    }

    public void setWeekData(String week_data) {
        this.weekData = week_data;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }

}
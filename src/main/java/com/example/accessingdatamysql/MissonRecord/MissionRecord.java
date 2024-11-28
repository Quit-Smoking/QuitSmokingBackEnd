package com.example.accessingdatamysql.MissonRecord;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="mission_records")
public class MissionRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "mission_id")
    private Integer missionId;

    private LocalDate date;

    @Column(name = "is_completed")
    private boolean isCompleted;

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

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getCompleted(){return isCompleted;}

    public void setCompleted(boolean completed){this.isCompleted = completed;}

}
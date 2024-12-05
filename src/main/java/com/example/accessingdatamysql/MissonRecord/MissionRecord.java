package com.example.accessingdatamysql.MissonRecord;

import com.example.accessingdatamysql.Mission.Mission;
import com.example.accessingdatamysql.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="mission_records")
public class MissionRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
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
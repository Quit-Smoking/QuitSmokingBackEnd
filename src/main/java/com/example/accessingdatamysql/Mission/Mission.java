package com.example.accessingdatamysql.Mission;

import com.example.accessingdatamysql.MissonRecord.MissionRecord;
import com.example.accessingdatamysql.User.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="missions")
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String mission;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "week_data")
    private String weekData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionRecord> missionRecords;

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
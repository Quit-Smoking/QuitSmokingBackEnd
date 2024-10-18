package com.example.accessingdatamysql;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="missions")
public class Mission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;

    private String mission;

    private boolean is_deleted;

    private boolean is_default;

    private int[] week_data = new int[7];


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

    public int[] getWeekData() {
        return week_data;
    }

    public void setWeekData(int[] week_data) {
        this.week_data = week_data;
    }

}
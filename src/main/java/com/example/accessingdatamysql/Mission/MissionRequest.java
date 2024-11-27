package com.example.accessingdatamysql.Mission;

import java.time.LocalDate;
import java.util.List;

public class MissionRequest {

    private String token;

    private String mission;

    private LocalDate start_date;

    private boolean is_deleted;

    private boolean is_default;

    private String week_data;

    public String getToken(){
        return token;
    }

    public String getMission(){
        return mission;
    }

    public LocalDate getStart_date() {
        return start_date;
    }
    public boolean getIs_deleted(){
        return is_deleted;
    }
    public boolean getIs_default(){
        return is_default;
    }
    public String getWeek_data(){
        return week_data;
    }

}

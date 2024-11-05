package com.example.accessingdatamysql.Mission;

public class MissionRequest {

    private String token;

    private String mission;

    private boolean is_deleted;

    private boolean is_default;

    private final int[] week_data = new int[7];

    public String getToken(){
        return token;
    }

    public String getMission(){
        return mission;
    }

    public boolean getIsDeleted(){
        return is_deleted;
    }
    public boolean getIsDefault(){
        return is_default;
    }
    public int[] getWeekData(){
        return week_data;
    }

}

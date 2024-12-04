package com.example.accessingdatamysql.NicotinDependencies;

import jakarta.persistence.Column;

public class NicotinDependenciesRequest {
    private Integer numberSmokedPerDayNum;

    private Integer firstSmokeTimeNum;

    private Integer hardToHoldSmokeNum;

    private Integer greatestSmokeOnDayNum;

    private Integer afternoonOrDinnerNum;

    private Integer youSickSmokeNum;

    public Integer getNumberSmokedPerDayNum() {
        return numberSmokedPerDayNum;
    }

    public Integer getFirstSmokeTimeNum() {
        return firstSmokeTimeNum;
    }

    public Integer getHardToHoldSmokeNum() {
        return hardToHoldSmokeNum;
    }

    public Integer getGreatestSmokeOnDayNum() {
        return greatestSmokeOnDayNum;
    }

    public Integer getAfternoonOrDinnerNum() {
        return afternoonOrDinnerNum;
    }

    public Integer getYouSickSmokeNum() {
        return youSickSmokeNum;
    }
}

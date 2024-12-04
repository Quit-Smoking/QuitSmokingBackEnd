package com.example.accessingdatamysql.NicotinDependencies;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "nicotin_dependencies")
public class NicotinDependencies {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "number_smoked_per_day_num")
    private Integer numberSmokedPerDayNum;

    @Column(name = "first_smoke_time_num")
    private Integer firstSmokeTimeNum;

    @Column(name = "hard_to_hold_smoke_num")
    private Integer hardToHoldSmokeNum;

    @Column(name = "greatest_smoke_on_day_num")
    private Integer greatestSmokeOnDayNum;

    @Column(name = "afternoon_or_dinner_num")
    private Integer afternoonOrDinnerNum;

    @Column(name = "you_sick_smoke_num")
    private Integer youSickSmokeNum;


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

    public Integer getNumberSmokedPerDayNum() {
        return numberSmokedPerDayNum;
    }

    public void setNumberSmokedPerDayNum(Integer numberSmokedPerDayNum) {
        this.numberSmokedPerDayNum = numberSmokedPerDayNum;
    }

    public Integer getFirstSmokeTimeNum() {
        return firstSmokeTimeNum;
    }

    public void setFirstSmokeTimeNum(Integer firstSmokeTimeNum) {
        this.firstSmokeTimeNum = firstSmokeTimeNum;
    }

    public Integer getHardToHoldSmokeNum() {
        return hardToHoldSmokeNum;
    }

    public void setHardToHoldSmokeNum(Integer hardToHoldSmokeNum) {
        this.hardToHoldSmokeNum = hardToHoldSmokeNum;
    }

    public Integer getGreatestSmokeOnDayNum() {
        return greatestSmokeOnDayNum;
    }

    public void setGreatestSmokeOnDayNum(Integer greatestSmokeOnDayNum) {
        this.greatestSmokeOnDayNum = greatestSmokeOnDayNum;
    }

    public Integer getAfternoonOrDinnerNum() {
        return afternoonOrDinnerNum;
    }

    public void setAfternoonOrDinnerNum(Integer afternoonOrDinnerNum) {
        this.afternoonOrDinnerNum = afternoonOrDinnerNum;
    }

    public Integer getYouSickSmokeNum() {
        return youSickSmokeNum;
    }

    public void setYouSickSmokeNum(Integer youSickSmokeNum) {
        this.youSickSmokeNum = youSickSmokeNum;
    }

}
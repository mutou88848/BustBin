package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Pony on 2018/11/25.
 */

public class HuoDongModel implements Serializable {


    /**
     * activityId : 1
     * activityUserName : 小丸子
     * activityYear : 25岁
     * activityUserId : 106
     * activityAddress : 西安市体育场
     * activityTime : 2018-11-25 21:14
     * activityName : 周末一起足球
     * activityMoney : 人均100元
     */

    private int activityId;
    private String activityNumber;
    private String activityUserName;
    private String activityYear;
    private String activityUserId;
    private String activityAddress;
    private String activityTime;
    private String activityName;
    private String activityMoney;

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityUserName() {
        return activityUserName;
    }

    public void setActivityUserName(String activityUserName) {
        this.activityUserName = activityUserName;
    }

    public String getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(String activityYear) {
        this.activityYear = activityYear;
    }

    public String getActivityUserId() {
        return activityUserId;
    }

    public void setActivityUserId(String activityUserId) {
        this.activityUserId = activityUserId;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(String activityMoney) {
        this.activityMoney = activityMoney;
    }
}

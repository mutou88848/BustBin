package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Pony on 2018/11/25.
 */

public class CircleModel implements Serializable {


    /**
     * circleAddress : 108.964516,34.222908
     * circleUserName : 小丸子
     * circleUserId : 106
     * circleMessgae : 长安足球爱好者看过来；
     * circleId : 1
     * circleTime : 2018-11-25 16:56
     * circleName : 足球人在长安
     */

    private String circleAddress;
    private String circleUserName;
    private String circleUserId;
    private String circleMessgae;
    private int circleId;
    private String circleTime;
    private String circleName;

    public String getCircleAddress() {
        return circleAddress;
    }

    public void setCircleAddress(String circleAddress) {
        this.circleAddress = circleAddress;
    }

    public String getCircleUserName() {
        return circleUserName;
    }

    public void setCircleUserName(String circleUserName) {
        this.circleUserName = circleUserName;
    }

    public String getCircleUserId() {
        return circleUserId;
    }

    public void setCircleUserId(String circleUserId) {
        this.circleUserId = circleUserId;
    }

    public String getCircleMessgae() {
        return circleMessgae;
    }

    public void setCircleMessgae(String circleMessgae) {
        this.circleMessgae = circleMessgae;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCircleTime() {
        return circleTime;
    }

    public void setCircleTime(String circleTime) {
        this.circleTime = circleTime;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }
}

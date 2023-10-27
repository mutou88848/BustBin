package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/11/26.
 */

public class CircleMessageModel implements Serializable {


    /**
     * circlemsgMessage : 篮球鞋即SNEAKER，同时也将球鞋爱好者融入到SNEAKER一词中，使得SNEAKER有更宽泛的定义。 篮球是一项剧烈运动，为了能应付激烈的运动，对于一双篮球鞋来讲，就需要有很好的耐久性，支撑性、稳定性，舒适性和良好的减震作用；
     * circlemsgUserName : 小丸子
     * circlemsgUserId : 106
     * circlemsgId : 3
     * circlemsgTime : 2018-11-26 10:50
     * circleId : 1
     * circlemsgImg : qxlarge-dsc-0C1BA41B910E98822A9E025B84C36863.jpg
     */

    private String circlemsgMessage;
    private String circlemsgUserName;
    private String circlemsgUserId;
    private int circlemsgId;
    private String circlemsgTime;
    private String circleId;
    private String circlemsgImg;

    public String getCirclemsgMessage() {
        return circlemsgMessage;
    }

    public void setCirclemsgMessage(String circlemsgMessage) {
        this.circlemsgMessage = circlemsgMessage;
    }

    public String getCirclemsgUserName() {
        return circlemsgUserName;
    }

    public void setCirclemsgUserName(String circlemsgUserName) {
        this.circlemsgUserName = circlemsgUserName;
    }

    public String getCirclemsgUserId() {
        return circlemsgUserId;
    }

    public void setCirclemsgUserId(String circlemsgUserId) {
        this.circlemsgUserId = circlemsgUserId;
    }

    public int getCirclemsgId() {
        return circlemsgId;
    }

    public void setCirclemsgId(int circlemsgId) {
        this.circlemsgId = circlemsgId;
    }

    public String getCirclemsgTime() {
        return circlemsgTime;
    }

    public void setCirclemsgTime(String circlemsgTime) {
        this.circlemsgTime = circlemsgTime;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCirclemsgImg() {
        return circlemsgImg;
    }

    public void setCirclemsgImg(String circlemsgImg) {
        this.circlemsgImg = circlemsgImg;
    }
}

package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2019/2/22.
 */

public class SceneryModel implements Serializable {


    /**
     * sceneryAddress : 西安市临潼区
     * sceneryCoordinate : 108.92279,34.241026
     * sceneryImage : 1505283939521.jpg
     * sceneryTitle : 秦始皇兵马俑博物馆
     * sceneryMessage : 秦始皇兵马俑博物馆，又称兵马俑、秦兵马俑，是秦始皇陵的陪葬坑，和秦始皇帝陵博物馆为同一个景点，采用一票制。秦始皇兵马俑素有世界第八大奇迹之称，这里出土的一千多个士兵陶俑，形象各不相同，神态生动，是中国古代雕塑艺术史上的一颗明珠，被誉为“二十世纪考古史上的伟大发现之一”。
     * sceneryId : 2
     * sceneryMoney : 150
     * sceneryTime : 2019-02-act act:21
     */

    private String sceneryAddress;
    private String sceneryCoordinate;
    private String sceneryImage;
    private String sceneryTitle;
    private String sceneryMessage;
    private int sceneryId;
    private String sceneryMoney;
    private String sceneryTime;

    public String getSceneryAddress() {
        return sceneryAddress;
    }

    public void setSceneryAddress(String sceneryAddress) {
        this.sceneryAddress = sceneryAddress;
    }

    public String getSceneryCoordinate() {
        return sceneryCoordinate;
    }

    public void setSceneryCoordinate(String sceneryCoordinate) {
        this.sceneryCoordinate = sceneryCoordinate;
    }

    public String getSceneryImage() {
        return sceneryImage;
    }

    public void setSceneryImage(String sceneryImage) {
        this.sceneryImage = sceneryImage;
    }

    public String getSceneryTitle() {
        return sceneryTitle;
    }

    public void setSceneryTitle(String sceneryTitle) {
        this.sceneryTitle = sceneryTitle;
    }

    public String getSceneryMessage() {
        return sceneryMessage;
    }

    public void setSceneryMessage(String sceneryMessage) {
        this.sceneryMessage = sceneryMessage;
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }

    public String getSceneryMoney() {
        return sceneryMoney;
    }

    public void setSceneryMoney(String sceneryMoney) {
        this.sceneryMoney = sceneryMoney;
    }

    public String getSceneryTime() {
        return sceneryTime;
    }

    public void setSceneryTime(String sceneryTime) {
        this.sceneryTime = sceneryTime;
    }
}

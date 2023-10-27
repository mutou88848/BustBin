package com.clientBase.banner.bean;

/**
 * Created by Administrator on 2023/4/16.
 */

public class Message {
    private String stationd;
    private String stationCoordinate;

    public Message(String stationd,  String stationCoordinate) {
        this.stationd = stationd;
        this.stationCoordinate = stationCoordinate;
    }

    public String getStationd() {
        return stationd;
    }

    public void setStationd(String stationd) {
        this.stationd = stationd;
    }


    public String getStationCoordinate() {
        return stationCoordinate;
    }

    public void setStationCoordinate(String stationCoordinate) {
        this.stationCoordinate = stationCoordinate;
    }
}

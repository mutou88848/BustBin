package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by Pony on 2019/2/26.
 */

public class StationModel implements Serializable {


    /**
     * stationCoordinate : 108.92279,34.241026

     * stationTime : 2019-02-11 11:21
     * stationImage : 1505283939521.jpg
     * stationPower : 1000
     * stationId : 2
     * stationMoney : 20
     * stationType : 交流电
     * stationAddress : 西安市临潼区
     */

    private String stationCoordinate;
    private String stationTitle;
    private String fullEmpty;
    private String stationTime;
    private String actionDump;
    private String type;
    private String status;
    private int stationId;

    public String getStationCoordinate() {
        return stationCoordinate;
    }

    public void setStationCoordinate(String stationCoordinate) {
        this.stationCoordinate = stationCoordinate;
    }

    public String getstationTitle() {
        return stationTitle;
    }
    public String getfullEmpty() {
        return fullEmpty;
    }
    public String getactionDump() {
        return actionDump;
    }
    public String gettype() {
        return type;
    }
    public String getstatus() {
        return status;
    }


    public void setstationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }
    public void setfullEmpty(String fullEmpty) {
        this.fullEmpty = fullEmpty;
    }
    public void setactionDump(String actionDump) {
        this.fullEmpty = actionDump;
    }
    public void settype(String type) {
        this.type = type;
    }
    public void setstatus(String status) {
        this.status = status;
    }

    public String getStationTime() {
        return stationTime;
    }

    public void setStationTime(String stationTime) {
        this.stationTime = stationTime;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}

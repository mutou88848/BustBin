package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/9/11.
 */

public class RepairModel implements Serializable {


    /**
     * repairTime : 2020-09-11 15:20
     * repairUserId : 106
     * repairAddress : 108.92279,34.241026
     * repairInfor : 自信车前轮被人卸了，你们安排人员来看看
     * repairStationId : 202001
     * repairUserName : 小丸子
     * repairId : 1
     */

    private String repairTime;
    private int repairUserId;
    private String repairAddress;
    private String repairInfor;
    private int repairStationId;
    private String repairUserName;
    private int repairId;

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public int getRepairUserId() {
        return repairUserId;
    }

    public void setRepairUserId(int repairUserId) {
        this.repairUserId = repairUserId;
    }

    public String getRepairAddress() {
        return repairAddress;
    }

    public void setRepairAddress(String repairAddress) {
        this.repairAddress = repairAddress;
    }

    public String getRepairInfor() {
        return repairInfor;
    }

    public void setRepairInfor(String repairInfor) {
        this.repairInfor = repairInfor;
    }

    public int getRepairStationId() {
        return repairStationId;
    }

    public void setRepairStationId(int repairStationId) {
        this.repairStationId = repairStationId;
    }

    public String getRepairUserName() {
        return repairUserName;
    }

    public void setRepairUserName(String repairUserName) {
        this.repairUserName = repairUserName;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }
}

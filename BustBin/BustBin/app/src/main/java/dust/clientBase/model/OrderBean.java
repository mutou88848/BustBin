package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/9/17.
 */

public class OrderBean implements Serializable {


    /**
     * orderTimeLong : 01:14
     * orderMessageId : 2
     * orderLatLng : 34.241026,108.92279-34.241016,108.925409-34.241015,108.925410-34.240847,108.928370-34.243615,108.932236-34.245346,108.932237-34.245347,108.932237-34.244876,108.930106-34.244877,108.930106-34.245918,108.929348-34.246081,108.925971-34.246020,108.923316-34.248973,108.923409
     * orderUserId : 106
     * orderTime : 2020-09-10 17:46
     * orderMoney : 1
     * orderUserName : 小丸子
     * orderId : 1
     * orderMessageName : 1001
     */

    private String orderTimeLong;
    private String orderMessageId;
    private String orderLatLng;
    private String orderUserId;
    private String orderTime;
    private String orderMoney;
    private String orderUserName;
    private int orderId;
    private String orderMessageName;

    private String orderAddressStart;
    private String orderAddressEnd;

    public String getOrderAddressStart() {
        return orderAddressStart;
    }

    public void setOrderAddressStart(String orderAddressStart) {
        this.orderAddressStart = orderAddressStart;
    }

    public String getOrderAddressEnd() {
        return orderAddressEnd;
    }

    public void setOrderAddressEnd(String orderAddressEnd) {
        this.orderAddressEnd = orderAddressEnd;
    }

    public String getOrderTimeLong() {
        return orderTimeLong;
    }

    public void setOrderTimeLong(String orderTimeLong) {
        this.orderTimeLong = orderTimeLong;
    }

    public String getOrderMessageId() {
        return orderMessageId;
    }

    public void setOrderMessageId(String orderMessageId) {
        this.orderMessageId = orderMessageId;
    }

    public String getOrderLatLng() {
        return orderLatLng;
    }

    public void setOrderLatLng(String orderLatLng) {
        this.orderLatLng = orderLatLng;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderMessageName() {
        return orderMessageName;
    }

    public void setOrderMessageName(String orderMessageName) {
        this.orderMessageName = orderMessageName;
    }
}

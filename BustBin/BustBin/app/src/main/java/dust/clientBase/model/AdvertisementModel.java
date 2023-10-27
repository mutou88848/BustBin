package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/11/28.
 */

public class AdvertisementModel implements Serializable{


    /**
     * advertisementTime : 2018-11-28 16:43:48
     * advertisementMessage : 在TI8期间，V社公布了TI9将在中国上海举办的消息，让国内很多玩家为之兴奋。不过前段时间出现了Skem、Kuku等人的辱华事件，V社只给了一个迟到的声明；最近还传出重庆Major和TI9或将禁赛Kuku、Skem，引起国外强烈的反响，有些激进的玩家发言希望V社取消在中国举办TI9的决定，不过现在看来，TI9是稳稳的会在上海举办了，而辱华和禁赛事件，V社之后应该也会给出明确的答复吧
     * advertisementName : 完美世界与V社完成签约仪式
     * advertisementPhone : 15249248888
     * advertisementId : 1
     * advertisementImg : dota_7.jpg
     */

    private String advertisementTime;
    private String advertisementMessage;
    private String advertisementName;
    private String advertisementPhone;
    private int advertisementId;
    private String advertisementImg;

    public String getAdvertisementTime() {
        return advertisementTime;
    }

    public void setAdvertisementTime(String advertisementTime) {
        this.advertisementTime = advertisementTime;
    }

    public String getAdvertisementMessage() {
        return advertisementMessage;
    }

    public void setAdvertisementMessage(String advertisementMessage) {
        this.advertisementMessage = advertisementMessage;
    }

    public String getAdvertisementName() {
        return advertisementName;
    }

    public void setAdvertisementName(String advertisementName) {
        this.advertisementName = advertisementName;
    }

    public String getAdvertisementPhone() {
        return advertisementPhone;
    }

    public void setAdvertisementPhone(String advertisementPhone) {
        this.advertisementPhone = advertisementPhone;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getAdvertisementImg() {
        return advertisementImg;
    }

    public void setAdvertisementImg(String advertisementImg) {
        this.advertisementImg = advertisementImg;
    }
}

package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/9/14.
 */

public class TopicBean implements Serializable{


    private String topicUserName;
    private String topicImg;
    private String topicUserId;
    private String topicMessage;
    private int topicId;
    private String topicTime;

    public String getTopicUserName() {
        return topicUserName;
    }

    public void setTopicUserName(String topicUserName) {
        this.topicUserName = topicUserName;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(String topicUserId) {
        this.topicUserId = topicUserId;
    }

    public String getTopicMessage() {
        return topicMessage;
    }

    public void setTopicMessage(String topicMessage) {
        this.topicMessage = topicMessage;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime;
    }
}

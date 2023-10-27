package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/9/11.
 */

public class NewsModel implements Serializable {


    /**
     * newsTime : 2020-08-26 16:52
     * newsId : 8
     * newsMessage : 同时，也可以加强对工作人员工作质量的监督，派专门的人员对工作人员的打扫结果进行检查，对打扫质量好的工作人员进行奖励，对打扫不合格的工作人员进行适当的处罚;此外，也可以加强对工作人员的培训，让大家对自己的工作负责，对城市环卫建设负责，自觉做好卫生维护工作。
     * newsTitle : 计算机基础编程
     */

    private String newsTime;
    private int newsId;
    private String newsMessage;
    private String newsTitle;

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsMessage() {
        return newsMessage;
    }

    public void setNewsMessage(String newsMessage) {
        this.newsMessage = newsMessage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}

package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/9/6.
 */

public class NoticeBean implements Serializable{


    /**
     * newsTime : 2018-09-11 16:55
     * newsId : 35
     * newsContent : 开学第一天，老师应亲切接待，叫着他的名字，拥抱他，和他说说话，以形体姿态给他温暖的体验。可以给他喜欢的玩具，或带他玩游戏设施，使初入园的儿童感到乐趣。对于哭闹的孩子，老师要像妈妈一样，哄抱一下，给以抚慰和鼓励，可以当着孩子的面请家长许诺尽早来接他。对于一些虽不哭，但内心焦虑烦燥不安的孩子，也要给予必要的关注。总之，应关注到每一个孩子
     * departmentMsg : 校务处
     * newsTitle : 对怎么做好新生接待的通知说明
     */

    private String newsTime;
    private int newsId;
    private String newsContent;
    private String newsType;
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

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}

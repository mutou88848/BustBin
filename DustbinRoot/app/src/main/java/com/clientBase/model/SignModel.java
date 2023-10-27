package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/11/30.
 */

public class SignModel implements Serializable {


    /**
     * signId : 1
     * signUserName : 小丸子
     * signUserId : 106
     * signCreatime : 2018-11-30
     */

    private int signId;
    private String signUserName;
    private String signUserId;
    private String signCreatime;

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public String getSignUserId() {
        return signUserId;
    }

    public void setSignUserId(String signUserId) {
        this.signUserId = signUserId;
    }

    public String getSignCreatime() {
        return signCreatime;
    }

    public void setSignCreatime(String signCreatime) {
        this.signCreatime = signCreatime;
    }
}

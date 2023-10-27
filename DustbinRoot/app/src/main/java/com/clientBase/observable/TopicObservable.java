package com.clientBase.observable;

import java.util.Observable;

public class TopicObservable extends Observable {

    //单例
    private static TopicObservable instance = null;

    public static TopicObservable getInstance() {

        if (null == instance) {
            instance = new TopicObservable();
        }
        return instance;
    }

    //通知观察者更新数据
    public void notifyStepChange(String msg) {
        setChanged();//设置changeFlag
        notifyObservers(msg);//通知观察者
    }

}
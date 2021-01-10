package com.kevin.spring.event;

import com.kevin.base.utils.PrintUtil;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer}demo
 *
 * @Author:Kevin
 * @Date:Created in 11:17 2021/1/10
 */
public class ObserverDemo {
    public static void main(String[] args) {
        EventObservable observable = new EventObservable();

        //添加观察者（监听者）
        observable.addObserver(new EventObserver());

        //发布消息（事件）
        observable.notifyObservers("hello world");
    }

    static class EventObservable extends Observable {

        public void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer ,EventListener{

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            PrintUtil.print("received event : " + eventObject);
        }
    }
}

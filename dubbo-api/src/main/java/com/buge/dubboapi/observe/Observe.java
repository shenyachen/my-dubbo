package com.buge.dubboapi.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: yachen.shen
 * @Date 2020/3/21 0:49
 */
public class Observe implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("射精");
    }
}

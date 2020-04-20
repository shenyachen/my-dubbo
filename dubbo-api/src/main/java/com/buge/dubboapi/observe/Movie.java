package com.buge.dubboapi.observe;

import java.util.Observable;

/**
 * @author: yachen.shen
 * @Date 2020/3/21 0:52
 */
public class Movie extends Observable {

    public void move() {
        setChanged();
        notifyObservers();
    }

    public static void main(String[] args) {
        Observe observe = new Observe();
        Movie movie = new Movie();
        movie.addObserver(observe);
        movie.deleteObserver(observe);
        movie.move();
    }
}

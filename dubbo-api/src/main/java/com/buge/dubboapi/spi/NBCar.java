package com.buge.dubboapi.spi;

import com.alibaba.dubbo.common.URL;

/**
 * @author: yachen.shen
 * @Date 2020/3/14 12:27
 *
 * dubbo spi 可实现aop代理功能、set依赖注入
 */
public class NBCar implements Car{
    public Car car;
    /*public NBCar(Car car) {
        this.car = car;
    }*/

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String getColor() {
        System.out.println("=========");
        return car.getColor();
    }
}

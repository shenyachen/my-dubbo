package com.buge.dubboapi.spi;

import com.alibaba.dubbo.common.URL;

/**
 * @author: yachen.shen
 * @Date 2020/3/14 11:48
 */
public class BlackCar implements Car{
    @Override
    public String getColor() {
        return "black";
    }
}

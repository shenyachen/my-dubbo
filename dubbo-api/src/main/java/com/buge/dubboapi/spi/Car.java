package com.buge.dubboapi.spi;


import org.apache.dubbo.common.extension.SPI;

/**
 * @author: yachen.shen
 * @Date 2020/3/14 11:47
 */
@SPI
public interface Car {
    //@Adaptive("getColor")
    String getColor();
}

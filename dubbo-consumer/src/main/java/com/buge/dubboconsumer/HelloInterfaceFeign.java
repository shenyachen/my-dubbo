package com.buge.dubboconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yachen.shen
 * @Date 2020/3/28 17:21
 */
@FeignClient("SERVER-PRODUCT")
public interface HelloInterfaceFeign {

    @RequestMapping("/hi")
    public String hi();
}

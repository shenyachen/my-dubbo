package com.buge.dubboprovider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yachen.shen
 * @Date 2020/3/28 14:21
 */
@RestController
@RequestMapping("/")
public class HiController {

    @RequestMapping("hi")
    public String a() {
        return "provider1";
    }
}

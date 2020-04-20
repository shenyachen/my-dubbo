package com.buge.dubboapi.starter.annotation;

import com.buge.dubboapi.starter.service.Buge;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yachen.shen
 * @Date 2020/2/19 16:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(Buge.class)
public @interface EnableBuge {
}

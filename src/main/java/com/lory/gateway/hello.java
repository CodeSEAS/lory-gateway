package com.lory.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @RequestMapping(value="/hello", method = RequestMethod.GET) //定义路由
    public String hello() {
        return "hello SpringBoot";
    }

}

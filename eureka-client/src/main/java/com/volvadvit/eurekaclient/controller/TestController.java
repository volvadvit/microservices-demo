package com.volvadvit.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class TestController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    /**
     * To call through Gateway use <a href="http://localhost:8082/main/test">http://localhost:8082/main/test</a>
     */
    @GetMapping("/test")
    public String test() {
         return instanceId;
    }
}

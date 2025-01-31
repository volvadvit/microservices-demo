package com.volvadvit.kafkaproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadbalancer/server")
@RequiredArgsConstructor
public class LoadBalancerServerController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/health-check")
    public String hello() {
        return String.format("Hello from instance %s", instanceId);
    }
}

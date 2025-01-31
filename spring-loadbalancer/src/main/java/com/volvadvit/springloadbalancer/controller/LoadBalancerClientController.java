package com.volvadvit.springloadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loadbalancer/client")
public class LoadBalancerClientController {

    @Autowired
    private WebClient.Builder loadBalancedClient;

    @GetMapping("/checkServer")
    public Mono<String> sendRequestToServer() {
        return loadBalancedClient.build()
                .get()
                .uri("http://example-service/loadbalancer/server/health-check")
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> String.format("Server response: [%s]", response));
    }
}

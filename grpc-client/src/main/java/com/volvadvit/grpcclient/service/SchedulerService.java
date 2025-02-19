package com.volvadvit.grpcclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final GrpcClientService clientService;

    @Async
    @Scheduled(cron = "${scheduler.interval.cron}")
//    @Scheduled(cron = "@hourly")
    public void sendMessageToServer() {
        log.info("Sending message to server. Thread: " + Thread.currentThread().getName());
        String response = clientService.greet("Hello, it's client!");
        log.info("Received response from server: " + response);
    }
}


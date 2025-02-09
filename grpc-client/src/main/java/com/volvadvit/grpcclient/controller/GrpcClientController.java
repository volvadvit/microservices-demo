package com.volvadvit.grpcclient.controller;

import com.volvadvit.grpcclient.service.GrpcClientService;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam("name") String name) {

        return ResponseEntity.ok(grpcClientService.greet(name));
    }
}

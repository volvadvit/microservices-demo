package com.volvadvit.grpcclient.service;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.rpc.Status;
import com.volvadvit.gprc.GreetingServiceGrpc;
import com.volvadvit.gprc.HelloRequest;
import com.volvadvit.gprc.HelloResponse;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GrpcClientService {

    @GrpcClient("greeting")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceStub;

    public String greet(final String name) {

        final HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        try {
            final HelloResponse response = greetingServiceStub.greeting(request);
            log.info("timestamp: " + response.getResponseTimestamp() +
                    ", serverName: " + response.getName() +
                    ", message : " + response.getGreeting());
            return response.getGreeting();
        } catch (StatusRuntimeException statusEx) {
            return handleErrors(statusEx);
        }
    }

    private static String handleErrors(StatusRuntimeException statusEx) {
        final Status status = StatusProto.fromThrowable(statusEx);
        final List<String> errors = new ArrayList<>();
        for (Any any : status.getDetailsList()) {
            try {
                final HelloResponse exceptionResponse = any.unpack(HelloResponse.class);
                log.info("timestamp: " + exceptionResponse.getResponseTimestamp() +
                        ", error message : " + exceptionResponse.getGreeting());
                errors.add(exceptionResponse.getGreeting());
            } catch (InvalidProtocolBufferException ex) {
                log.error("Caught unsupported exception: {}", ex.getMessage());
                errors.add(ex.getMessage());
            }
        }
        return "Errors: " + String.join(", ", errors);
    }
}

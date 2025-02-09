package com.volvadvit.grpcserver.service;

import com.google.protobuf.Timestamp;
import com.volvadvit.gprc.GreetingServiceGrpc;
import com.volvadvit.gprc.HelloRequest;
import com.volvadvit.gprc.HelloResponse;
import com.volvadvit.grpcserver.exceptions.GrpcNotValidRequestException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;

@GrpcService
@Slf4j
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(final HelloRequest request, final StreamObserver<HelloResponse> responseObserver) {
        log.info("Received from a client: {}", request);

        if (request.getName().isEmpty()) {
            throw new GrpcNotValidRequestException("gRPC: Client Name cannot be empty");
        }

        final Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(Instant.now().getEpochSecond())
                .build();

        final HelloResponse response = HelloResponse.newBuilder()
                .setGreeting("Hello from the server, " + request.getName())
                .setName("gRPC Server")
                .setResponseTimestamp(timestamp)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}

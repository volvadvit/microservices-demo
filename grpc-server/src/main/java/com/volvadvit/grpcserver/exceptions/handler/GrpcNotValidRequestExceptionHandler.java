package com.volvadvit.grpcserver.exceptions.handler;

import com.google.protobuf.Any;
import com.google.protobuf.Timestamp;
import com.google.rpc.Code;
import com.google.rpc.Status;
import com.volvadvit.gprc.HelloResponse;
import com.volvadvit.grpcserver.exceptions.GrpcNotValidRequestException;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

import java.time.Instant;

@GrpcAdvice
public class GrpcNotValidRequestExceptionHandler {

    @GrpcExceptionHandler(GrpcNotValidRequestException.class)
    public StatusRuntimeException handler(final GrpcNotValidRequestException error) {

        final Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(Instant.now().getEpochSecond())
                .build();

        final HelloResponse errorResponse = com.volvadvit.gprc.HelloResponse.newBuilder()
                .setGreeting("Hello from the server, anonymous")
                .setName("gRPC Server")
                .setResponseTimestamp(timestamp)
                .build();

        final Status status = Status.newBuilder()
                .setCode(Code.INVALID_ARGUMENT.getNumber())
                .setMessage(error.getMessage())
                .addDetails(Any.pack(errorResponse))
                .build();

        return StatusProto.toStatusRuntimeException(status);
    }
}

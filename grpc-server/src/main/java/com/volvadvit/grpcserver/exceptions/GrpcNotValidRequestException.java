package com.volvadvit.grpcserver.exceptions;

public class GrpcNotValidRequestException extends RuntimeException {
    public GrpcNotValidRequestException(String message) {
        super(message);
    }
}

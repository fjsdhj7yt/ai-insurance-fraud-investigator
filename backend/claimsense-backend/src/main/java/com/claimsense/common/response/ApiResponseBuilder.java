package com.claimsense.common.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(HttpStatus status,
                                             String message,
                                             T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .status(status.value())
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Void> success(HttpStatus status,
                                            String message) {

        return ApiResponse.<Void>builder()
                .success(true)
                .status(status.value())
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
package com.claimsense.common.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {

    private boolean success;

    private int status;

    private String message;

    private T data;

    private LocalDateTime timestamp;
}
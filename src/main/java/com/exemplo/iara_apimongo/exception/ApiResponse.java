package com.exemplo.iara_apimongo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private int status;
    private Instant timestamp;
    private T data;

    public static <T> ApiResponse<T> of(String message, int status, T data) {
        return new ApiResponse<>(message, status, Instant.now(), data);
    }
}

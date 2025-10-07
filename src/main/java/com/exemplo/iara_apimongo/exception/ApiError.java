package com.exemplo.iara_apimongo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private Instant timestamp;
    private List<String> details;
}

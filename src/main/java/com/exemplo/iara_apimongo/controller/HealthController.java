package com.exemplo.iara_apimongo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Tag(name = "Health Controller", description = "Endpoint for checking the API status")
public class HealthController {
    @GetMapping
    @Operation(summary = "Checks if the API is running")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API running successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<String> health() {
        return ResponseEntity.ok( "OK");
    }
}

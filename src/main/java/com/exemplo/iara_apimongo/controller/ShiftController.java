package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.request.ShiftRequest;
import com.exemplo.iara_apimongo.model.dto.response.ShiftResponse;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/shifts")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Shifts", description = "Operations related to shifts")
public class ShiftController {

    private final ShiftService service;

    @Operation(summary = "Create a new shift")
    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponse>> create(@Valid @RequestBody ShiftRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Shift created successfully", HttpStatus.CREATED.value(),
                        service.create(request)));
    }

    @Operation(summary = "Find a shift by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponse>> findById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.of("Shift found", HttpStatus.OK.value(), service.findById(id)));
    }

    @Operation(summary = "List all shifts")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.of("All shifts retrieved", HttpStatus.OK.value(), service.findAll()));
    }


    @Operation(summary = "Delete a shift by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

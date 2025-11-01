package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.request.NameRequest;
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
        ShiftResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Shift created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Find a shift by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponse>> findById(@PathVariable String id) {
        ShiftResponse found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Shift found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all shifts")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponse>>> findAll() {
        List<ShiftResponse> found = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All shifts retrieved", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "Find a shift by name")
    @PostMapping("/by-name")
    public ResponseEntity<ApiResponse<ShiftResponse>> findByName(@RequestBody NameRequest request) {
        ShiftResponse found = service.findByName(request.getName());
        return ResponseEntity.ok(ApiResponse.of("Shift found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "Update an existing shift")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponse>> update(@PathVariable String id,
                                                             @Valid @RequestBody ShiftRequest dto) {
        ShiftResponse updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Shift updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Delete a shift by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Shift deleted successfully", HttpStatus.OK.value(), null));
    }

}

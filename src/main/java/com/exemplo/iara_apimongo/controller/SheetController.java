package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.request.SheetRequest;
import com.exemplo.iara_apimongo.model.dto.response.SheetResponse;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.SheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/sheets")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Sheets", description = "Operations related to production sheets")
public class SheetController {

    private final SheetService service;

    @Operation(summary = "Create a new production sheet")
    @PostMapping
    public ResponseEntity<ApiResponse<SheetResponse>> create(@Valid @RequestBody SheetRequest dto) {
        SheetResponse created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Sheet created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Find a production sheet by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponse>> findById(@PathVariable String id) {
        SheetResponse found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet found successfully", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all production sheets")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SheetResponse>>> findAll() {
        List<SheetResponse> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("Sheets retrieved successfully", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Delete a production sheet by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet deleted successfully", HttpStatus.OK.value(), null));
    }
}

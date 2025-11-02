package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.database.Sheet;
import com.exemplo.iara_apimongo.model.dto.request.SheetRequest;
import com.exemplo.iara_apimongo.model.dto.response.SheetResponse;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import com.exemplo.iara_apimongo.services.SheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/iara/api/sheets")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Sheets", description = "Operations related to sheets")
public class SheetController {

    private final SheetService service;

    @Operation(summary = "Create a new sheet (with Excel upload)")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<SheetResponse>> create(
            @RequestPart("data") @Valid SheetRequest dto,
            @RequestPart("file") MultipartFile file
    ) {
        SheetResponse created = service.createWithFile(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Sheet created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Find a sheet by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponse>> findById(@PathVariable String id) {
        SheetResponse found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet found successfully", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all sheets")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SheetResponse>>> findAll() {
        List<SheetResponse> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("Sheets retrieved successfully", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Delete a sheet by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet deleted successfully", HttpStatus.OK.value(), null));
    }

    @Operation(summary = "Get all sheets by factory ID", description = "Fetches all sheets associated with a specific factory ID.")
    @GetMapping("/factory/{factoryId}")
    public ResponseEntity<ApiResponse<List<SheetResponse>>> findByFactoryId(@PathVariable int factoryId) {
        List<SheetResponse> sheets = service.findByFactoryId(factoryId);
        return ResponseEntity.ok(ApiResponse.of(
                "Sheets retrieved successfully for factoryId " + factoryId,
                HttpStatus.OK.value(),
                sheets
        ));
    }

}

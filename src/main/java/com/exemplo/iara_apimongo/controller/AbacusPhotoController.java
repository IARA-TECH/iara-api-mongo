package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.request.AbacusPhotoRequest;
import com.exemplo.iara_apimongo.model.dto.request.ValidationRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusConfirmResponse;
import com.exemplo.iara_apimongo.model.dto.response.AbacusPhotoResponse;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.AbacusPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/iara/api/abacus-photos")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Abacus Photos", description = "Operations related to abacus photos")
public class AbacusPhotoController {

    private final AbacusPhotoService service;

    @Operation(summary = "Create a new abacus photo")
    @PostMapping
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> create(@Valid @RequestBody AbacusPhotoRequest dto) {
        AbacusPhotoResponse created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Abacus photo created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Update an existing abacus photo")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> update(@PathVariable String id,
                                                                   @Valid @RequestBody AbacusPhotoRequest dto) {
        AbacusPhotoResponse updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Find an abacus photo by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> findById(@PathVariable String id) {
        AbacusPhotoResponse found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all abacus photos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AbacusPhotoResponse>>> findAll() {
        List<AbacusPhotoResponse> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All abacus photos retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Delete an abacus photo by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo deleted successfully", HttpStatus.OK.value(), null));
    }

    @Operation(summary = "Validate sheet")
    @PutMapping("/validation/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> validate(@PathVariable String id,
                                                                     @Valid @RequestBody ValidationRequest request) {
        AbacusPhotoResponse validated = service.validate(id, request);
        return ResponseEntity.ok(ApiResponse.of("Sheet validated successfully", HttpStatus.OK.value(), validated));
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Object>> analyzeAbacus(
            @RequestPart("file") MultipartFile file,
            @RequestParam(required = false) String colors,
            @RequestParam(required = false) String values
    ) {
        Object result = service.sendToModel(file, colors, values);
        return ResponseEntity.ok(ApiResponse.of("Abacus analyzed successfully", HttpStatus.OK.value(), result));
    }

    @PostMapping(value = "/confirm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Confirm abacus data and upload photo + CSV")
    public ResponseEntity<ApiResponse<AbacusConfirmResponse>> confirmAbacus(
            @RequestPart("file") MultipartFile imageFile,
            @RequestPart("csv") MultipartFile csvFile,
            @RequestParam("factoryId") Integer factoryId,
            @RequestParam("shiftId") String shiftId,
            @RequestParam("takenBy") String takenBy,
            @RequestParam("abacusId") String abacusId
    ) {
        AbacusPhotoResponse photoResponse = service.saveConfirmedData(
                factoryId, shiftId, takenBy, abacusId, imageFile, csvFile
        );

        AbacusConfirmResponse confirmResponse = new AbacusConfirmResponse(
                photoResponse.getPhotoUrlBlob(),
                photoResponse.getSheetUrlBlob()
        );

        return ResponseEntity.ok(
                ApiResponse.of("Abacus photo and CSV saved successfully",
                        HttpStatus.OK.value(),
                        confirmResponse)
        );
    }

}

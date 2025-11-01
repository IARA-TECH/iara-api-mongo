package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.database.Abacus;
import com.exemplo.iara_apimongo.model.database.AbacusPhoto;
import com.exemplo.iara_apimongo.model.database.Sheet;
import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.request.AbacusPhotoRequest;
import com.exemplo.iara_apimongo.model.dto.request.ValidationRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusPhotoResponse;
import com.exemplo.iara_apimongo.repository.AbacusPhotoRepository;
import com.exemplo.iara_apimongo.repository.AbacusRepository;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import com.exemplo.iara_apimongo.util.scheduler.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequest, AbacusPhotoResponse> {

    private final ShiftRepository shiftRepository;
    private final AbacusRepository abacusRepository;
    private final SheetRepository sheetRepository;
    private final SupabaseStorageService supabaseStorageService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String abacusFolder = "foto_abacos";
    private final String sheetFolder = "sheets";
    private final String modelUrl = System.getenv("MODEL_URL");
    private final String modelKey = System.getenv("MODEL_KEY");

    public AbacusPhotoService(
            AbacusPhotoRepository repository,
            ShiftRepository shiftRepository,
            AbacusRepository abacusRepository,
            SheetRepository sheetRepository,
            SupabaseStorageService supabaseStorageService
    ) {
        super(repository, "AbacusPhoto");
        this.shiftRepository = shiftRepository;
        this.abacusRepository = abacusRepository;
        this.sheetRepository = sheetRepository;
        this.supabaseStorageService = supabaseStorageService;
    }

    public AbacusPhotoResponse saveConfirmedData(
            Integer factoryId,
            String shiftId,
            String takenBy,
            String abacusId,
            MultipartFile imageFile,
            MultipartFile csvFile
    ) {
        try {
            log.info("Saving confirmed data for factory {}, shift {}, abacus {}", factoryId, shiftId, abacusId);
            List<List<Integer>> values = CsvUtils.parseCsvToValues(csvFile);
            ByteArrayInputStream xlsxStream = CsvUtils.convertToExcel(values);

            MultipartFile excelFile = new MultipartFile() {
                @Override public String getName() { return "sheet.xlsx"; }
                @Override public String getOriginalFilename() { return "sheet.xlsx"; }
                @Override public String getContentType() { return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; }
                @Override public boolean isEmpty() { try { return xlsxStream.available() == 0; } catch (Exception e) { return true; } }
                @Override public long getSize() { try { return xlsxStream.available(); } catch (Exception e) { return 0; } }
                @Override public byte[] getBytes() throws IOException { return xlsxStream.readAllBytes(); }
                @Override public InputStream getInputStream() { return xlsxStream; }
                @Override public void transferTo(File dest) throws IOException { try (FileOutputStream out = new FileOutputStream(dest)) { out.write(getBytes()); } }
            };

            String imageUrl = supabaseStorageService.uploadFile(abacusFolder, imageFile);
            String sheetUrl = supabaseStorageService.uploadFile(sheetFolder, excelFile);

            Shift shift = shiftRepository.findById(shiftId)
                    .orElseThrow(() -> new ResourceNotFoundException("Shift not found: " + shiftId));
            Abacus abacus = abacusRepository.findById(abacusId)
                    .orElseThrow(() -> new ResourceNotFoundException("Abacus not found: " + abacusId));

            Sheet sheet = new Sheet();
            sheet.setFactoryId(factoryId);
            sheet.setShift(shift);
            sheet.setDate(Instant.now());
            sheet.setSheetUrlBlob(sheetUrl);
            sheetRepository.save(sheet);

            AbacusPhoto photo = AbacusPhoto.builder()
                    .factoryId(factoryId)
                    .shift(shift)
                    .abacus(abacus)
                    .takenBy(takenBy)
                    .takenAt(Instant.now())
                    .photoUrlBlob(imageUrl)
                    .sheetUrlBlob(sheetUrl)
                    .values(values)
                    .build();
            repository.save(photo);

            sheet.setAbacusPhotos(List.of(photo.getId()));
            sheetRepository.save(sheet);

            log.info("AbacusPhoto and Sheet saved successfully: {}", photo.getId());
            return toResponse(photo);

        } catch (Exception e) {
            log.error("Failed to save confirmed data: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save confirmed data: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> sendToModel(MultipartFile imageFile, String colors, String values) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + modelKey);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            ByteArrayResource resource = new ByteArrayResource(imageFile.getBytes()) {
                @Override public String getFilename() { return imageFile.getOriginalFilename(); }
            };
            body.add("file", resource);
            if (colors != null) body.add("colors", colors);
            if (values != null) body.add("values", values);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    modelUrl + "/analyze",
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to send to model: " + response.getStatusCode());
            }

            log.info("Model response received");
            return response.getBody();

        } catch (Exception e) {
            log.error("Error sending to model: {}", e.getMessage(), e);
            throw new RuntimeException("Error sending to model: " + e.getMessage(), e);
        }
    }

    public AbacusPhotoResponse validate(String id, ValidationRequest request) {
        AbacusPhoto photo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbacusPhoto not found: " + id));
        photo.setValidatedBy(request.getValidatedBy());
        repository.save(photo);
        return toResponse(photo);
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequest request) {
        throw new UnsupportedOperationException("Use saveConfirmedData() para criar AbacusPhoto com arquivos.");
    }

    @Override
    protected AbacusPhotoResponse toResponse(AbacusPhoto entity) {
        return AbacusPhotoResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .shiftId(entity.getShift() != null ? entity.getShift().getId() : null)
                .shiftName(entity.getShift() != null ? entity.getShift().getName() : null)
                .abacus(entity.getAbacus())
                .takenBy(entity.getTakenBy())
                .takenAt(entity.getTakenAt())
                .photoUrlBlob(entity.getPhotoUrlBlob())
                .sheetUrlBlob(entity.getSheetUrlBlob())
                .values(entity.getValues())
                .validatedBy(entity.getValidatedBy())
                .build();
    }
}

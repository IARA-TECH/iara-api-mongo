package com.exemplo.iara_apimongo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class SupabaseStorageService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String SUPABASE_URL = System.getenv("SUPABASE_URL");
    private final String SUPABASE_KEY = System.getenv("SUPABASE_KEY");
    private final String BUCKET_NAME = System.getenv("SUPABASE_BUCKET");

    public String uploadFile(String folder, MultipartFile file) throws Exception {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String uniqueId = UUID.randomUUID().toString().substring(0, 6);
        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        String prefix = "arquivo";
        if (extension.equalsIgnoreCase(".xlsx")) prefix = "planilha_abaco";
        else if (extension.equalsIgnoreCase(".csv")) prefix = "csv_abaco";
        else if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) prefix = "foto_abaco";
        else if (extension.equalsIgnoreCase(".png")) prefix = "imagem_abaco";

        String fileName = String.format("%s_%s_%s%s", prefix, timestamp, uniqueId, extension);

        String objectPath = folder + "/" + fileName;
        String url = SUPABASE_URL + "/storage/v1/object/" + BUCKET_NAME + "/" + objectPath;

        log.info("Uploading file {} to Supabase bucket {}", fileName, BUCKET_NAME);

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        HttpHeaders headers = new HttpHeaders();
        if (extension.equalsIgnoreCase(".xlsx")) {
            headers.setContentType(MediaType.parseMediaType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        } else if (extension.equalsIgnoreCase(".csv")) {
            headers.setContentType(MediaType.TEXT_PLAIN);
        } else if (extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".jpg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (extension.equalsIgnoreCase(".png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        } else {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }

        headers.set("Authorization", "Bearer " + SUPABASE_KEY);
        headers.set("apikey", SUPABASE_KEY);

        HttpEntity<ByteArrayResource> requestEntity = new HttpEntity<>(resource, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Upload failed: {}", response.getBody());
            throw new RuntimeException("Failed to upload file: " + response.getStatusCode() + " " + response.getBody());
        }

        log.info("Upload successful for {}", fileName);

        return SUPABASE_URL + "/storage/v1/object/public/" + BUCKET_NAME + "/" + objectPath;
    }
}

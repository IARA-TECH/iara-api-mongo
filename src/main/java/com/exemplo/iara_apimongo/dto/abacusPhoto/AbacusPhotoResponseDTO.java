package com.exemplo.iara_apimongo.dto.abacusPhoto;

import com.exemplo.iara_apimongo.model.Abacus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbacusPhotoResponseDTO {
    private String id;
    private int factoryId;
    private String shiftId;
    private String abacusId;
    private String takenBy;
    private LocalDateTime takenAt;
    private String urlBlob;
    private String validatedBy;
    private List<String> lines;
    private List<Abacus.AbacusColumn> columns;
    private List<List<Integer>> values;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}


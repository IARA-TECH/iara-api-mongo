package com.exemplo.iara_apimongo.model.dto.response;

import com.exemplo.iara_apimongo.model.database.Abacus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbacusPhotoResponse {
    private String id;
    private int factoryId;
    private String takenBy;
    private Instant takenAt;
    private String validatedBy;
    private String photoUrlBlob;
    private String sheetUrlBlob;
    private Abacus abacus;
    private List<List<Integer>> values;
    private String shiftId;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
    private Instant shiftCreatedAt;
}

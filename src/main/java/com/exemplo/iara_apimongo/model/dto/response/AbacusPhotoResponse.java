package com.exemplo.iara_apimongo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbacusPhotoResponse {
    private String id;
    private int factoryId;
    private String shiftId;
    private String abacusId;
    private String takenBy;
    private LocalDateTime takenAt;
    private LocalDateTime date;
    private String photoUrlBlob;
    private String sheetUrlBlob;
    private String validatedBy;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}

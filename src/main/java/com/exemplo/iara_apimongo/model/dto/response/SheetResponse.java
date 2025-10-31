package com.exemplo.iara_apimongo.model.dto.response;

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
public class SheetResponse {
    private String id;
    private int factoryId;
    private List<String> abacusPhotoIds;
    private Instant date;
    private String sheetUrlBlob;
    private String shiftId;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}

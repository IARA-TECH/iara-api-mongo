package com.exemplo.iara_apimongo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SheetResponse {
    private String id;
    private int factoryId;
    private String shiftId;
    private List<String> abacusPhotoIds;
    private LocalDateTime date;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}

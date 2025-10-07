package com.exemplo.iara_apimongo.dto.sheet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SheetResponseDTO {
    private String id;
    private int factoryId;
    private String shiftId;
    private List<String> abacusPhotoIds;
    private LocalDate date;
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}

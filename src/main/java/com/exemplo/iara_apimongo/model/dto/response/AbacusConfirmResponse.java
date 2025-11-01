package com.exemplo.iara_apimongo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AbacusConfirmResponse {
    private String photoUrl;
    private String sheetUrl;
}

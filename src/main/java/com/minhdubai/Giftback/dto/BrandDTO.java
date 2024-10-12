package com.minhdubai.Giftback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDTO {
    private String name;
    private String description;
    private String logoPath;
}

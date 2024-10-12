package com.minhdubai.Giftback.domain.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Integer id;
    private String name;
    private String description;
    private String logoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.minhdubai.Giftback.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiMapDto {
    private Integer id;

    @JsonBackReference
    private AffiliateNetworkDto affiliateNetwork;
    
    private String getCampaignApi;
    private String getProductApi;
    private String getTransactionApi;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

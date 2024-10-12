package com.minhdubai.Giftback.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateNetworkDto {
    private Integer id;
    private String name;
    private String url;
    private String authorizeToken;

    @JsonManagedReference
    private ApiMapDto apiMap;
}

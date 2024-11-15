package com.minhdubai.Giftback.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Integer id;
    private String name;

    @Builder.Default
    private BigDecimal walletBalance = BigDecimal.ZERO;
    
    @JsonIgnoreProperties("group")
    private UserDto owner;

    @JsonIgnore
    private Set<UserDto> users;

    private Integer memberCount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

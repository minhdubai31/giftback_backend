package com.minhdubai.Giftback.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minhdubai.Giftback.domain.constant.PayoutMethod;
import com.minhdubai.Giftback.domain.constant.PayoutStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutDto {
    private Integer id;

    @JsonIgnoreProperties({"group", "notifications"})
    private UserDto user;
    private BigDecimal amount;
    private PayoutStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

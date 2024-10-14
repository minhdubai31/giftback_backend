package com.minhdubai.Giftback.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private UserDto user;
    private BigDecimal amount;
    private PayoutMethod method;
    private PayoutStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;
}

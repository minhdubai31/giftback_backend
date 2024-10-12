package com.minhdubai.Giftback.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer id;
    private UserDto user;
    private AffiliateProgramDto program;
    private BigDecimal totalAmount;
    private BigDecimal cashbackAmount;
    private LocalDateTime transactionDate;
}

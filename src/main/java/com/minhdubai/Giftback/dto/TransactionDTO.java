package com.minhdubai.Giftback.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    private Integer userId;
    private Integer programId;
    private BigDecimal totalAmount;
    private BigDecimal cashbackAmount;
}

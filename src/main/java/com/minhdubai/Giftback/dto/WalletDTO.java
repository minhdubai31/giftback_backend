package com.minhdubai.Giftback.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDTO {
    private Integer userId;
    private BigDecimal balance;
}

package com.minhdubai.Giftback.dto;

import java.math.BigDecimal;

import com.minhdubai.Giftback.domain.constant.PayoutMethod;
import com.minhdubai.Giftback.domain.constant.PayoutStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayoutDTO {
    private Integer userId;
    private BigDecimal amount;
    private PayoutMethod method;
    private PayoutStatus status;
}

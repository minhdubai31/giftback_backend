package com.minhdubai.Giftback.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String transactionId;

    private UserDto user;
    private AffiliateProgramDto program;
    private BigDecimal totalAmount;
    private BigDecimal cashbackAmount;
    private LocalDateTime transactionDate;
    @Builder.Default
    private Integer status = 0; // 0: Pending, 1: Approved, 2: Rejected

    private String productImage;

    private String productName;

    private String reasonReject;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

package com.minhdubai.Giftback.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "payouts")
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PayoutMethod method;

    @Enumerated(EnumType.STRING)
    private PayoutStatus status;

    @CreationTimestamp
    private LocalDateTime requestedAt;

    private LocalDateTime completedAt;

    public enum PayoutMethod {
        BANK_TRANSFER
    }

    public enum PayoutStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
}

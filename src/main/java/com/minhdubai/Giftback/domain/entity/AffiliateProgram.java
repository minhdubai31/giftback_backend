package com.minhdubai.Giftback.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "affiliate_programs")
public class AffiliateProgram {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String campaignId;

    private String programName;
    @Column(columnDefinition = "TEXT")
    private String commissionRate;

    @Column(columnDefinition = "TEXT")
    private String terms;

    private String logo;

    @ManyToOne
    @JoinColumn(name = "network_id")
    private AffiliateNetwork affiliateNetwork;

    private String programUrl;

    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

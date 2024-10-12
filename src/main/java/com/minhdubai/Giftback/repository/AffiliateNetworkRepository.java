package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateNetworkRepository extends JpaRepository<AffiliateNetwork, Integer> {
    // Additional query methods can be defined here
}

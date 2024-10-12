package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.AffiliateProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateProgramRepository extends JpaRepository<AffiliateProgram, Integer> {
    // Additional query methods can be defined here
}

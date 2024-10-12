package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Additional query methods can be defined here
}

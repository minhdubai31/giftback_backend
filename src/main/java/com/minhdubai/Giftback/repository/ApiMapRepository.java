package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.ApiMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiMapRepository extends JpaRepository<ApiMap, Integer> {
    // Additional query methods can be defined here
}

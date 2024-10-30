package com.minhdubai.Giftback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdubai.Giftback.domain.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    // Additional query methods can be defined here
}

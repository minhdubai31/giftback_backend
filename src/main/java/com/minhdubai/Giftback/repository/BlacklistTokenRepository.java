package com.minhdubai.Giftback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdubai.Giftback.domain.entity.BlacklistToken;

public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, String> {

}

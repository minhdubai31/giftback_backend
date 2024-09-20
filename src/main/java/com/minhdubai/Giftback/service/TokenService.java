package com.minhdubai.Giftback.service;

import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.repository.BlacklistTokenRepository;

@Service
public class TokenService {
   private BlacklistTokenRepository blacklistTokenRepository;

   public boolean isBlacklisted(String token) {
      return blacklistTokenRepository.existsById(token);
   }
}

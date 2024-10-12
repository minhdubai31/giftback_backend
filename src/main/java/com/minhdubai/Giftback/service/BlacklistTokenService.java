package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.BlacklistToken;
import com.minhdubai.Giftback.repository.BlacklistTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistTokenService {
   @Autowired
   private BlacklistTokenRepository blacklistTokenRepository;

   public BlacklistToken createBlacklistToken(BlacklistToken blacklistToken) {
      return blacklistTokenRepository.save(blacklistToken);
   }

   public List<BlacklistToken> getAllBlacklistTokens() {
      return blacklistTokenRepository.findAll();
   }

   public BlacklistToken getBlacklistTokenById(String token) {
      return blacklistTokenRepository.findById(token).orElse(null);
   }

   public BlacklistToken updateBlacklistToken(BlacklistToken blacklistToken) {
      return blacklistTokenRepository.save(blacklistToken);
   }

   public void deleteBlacklistToken(String token) {
      blacklistTokenRepository.deleteById(token);
   }
}

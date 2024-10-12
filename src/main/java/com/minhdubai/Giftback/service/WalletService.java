package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.domain.entity.Wallet;
import com.minhdubai.Giftback.dto.WalletDTO;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
   private WalletRepository walletRepository;
   private UserRepository userRepository;

   public Wallet createWallet(WalletDTO walletDTO) {
      User user = userRepository.findById(walletDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + walletDTO.getUserId()));
      Wallet wallet = Wallet.builder()
            .user(user)
            .balance(walletDTO.getBalance())
            .build();
      return walletRepository.save(wallet);
   }

   public List<Wallet> getAllWallets() {
      return walletRepository.findAll();
   }

   public Wallet getWalletById(Integer id) {
      return walletRepository.findById(id).orElse(null);
   }

   public Wallet updateWallet(Integer id, WalletDTO walletDTO) {
      Wallet wallet = walletRepository.findById(id).orElse(null);
      if (wallet != null) {
         User user = userRepository.findById(walletDTO.getUserId())
               .orElseThrow(() -> new RuntimeException("User not found with ID: " + walletDTO.getUserId()));
         wallet.setUser(user);
         wallet.setBalance(walletDTO.getBalance());
         return walletRepository.save(wallet);
      }
      return null;
   }

   public void deleteWallet(Integer id) {
      walletRepository.deleteById(id);
   }
}

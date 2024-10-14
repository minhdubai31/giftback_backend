package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.domain.entity.Wallet;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.WalletDto;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.repository.WalletRepository;
import org.springframework.stereotype.Service;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.util.List;

@Service
public class WalletService {
   private WalletRepository walletRepository;
   private UserRepository userRepository;
   private Mapper<Wallet, WalletDto> walletMapper;

   public ResponseDto createWallet(WalletDto walletDto) {
      User user = userRepository.findById(walletDto.getUser().getId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + walletDto.getUser().getId()));
      Wallet wallet = Wallet.builder()
            .user(user)
            .balance(walletDto.getBalance())
            .build();
      Wallet savedWallet = walletRepository.save(wallet);
      WalletDto savedWalletDto = walletMapper.mapTo(savedWallet);
      return ResponseDto.builder()
            .status(201)
            .message("Wallet created successfully")
            .data(savedWalletDto)
            .build();
   }

   public ResponseDto getAllWallets() {
      List<Wallet> wallets = walletRepository.findAll();
      List<WalletDto> walletDtos = wallets.stream()
            .map(walletMapper::mapTo)
            .toList();
      return ResponseDto.builder()
            .status(200)
            .message("Wallets retrieved successfully")
            .data(walletDtos)
            .build();
   }

   public ResponseDto getWalletById(Integer id) {
      Wallet wallet = walletRepository.findById(id).orElse(null);
      if (wallet != null) {
         WalletDto walletDto = walletMapper.mapTo(wallet);
         return ResponseDto.builder()
                 .status(200)
                 .message("Wallet found")
                 .data(walletDto)
                 .build();
      }
      return ResponseDto.builder()
              .status(404)
              .message("Wallet not found")
              .build();
   }

   public ResponseDto updateWallet(Integer id, WalletDto walletDto) {
      Wallet wallet = walletRepository.findById(id).orElse(null);
      if (wallet != null) {
         User user = userRepository.findById(walletDto.getUser().getId())
               .orElseThrow(() -> new RuntimeException("User not found with ID: " + walletDto.getUser().getId()));
         wallet.setUser(user);
         wallet.setBalance(walletDto.getBalance());
         Wallet updatedWallet = walletRepository.save(wallet);
         WalletDto updatedWalletDto = walletMapper.mapTo(updatedWallet);
         return ResponseDto.builder()
               .status(200)
               .message("Wallet updated successfully")
               .data(updatedWalletDto)
               .build();
      }
      return ResponseDto.builder()
              .status(404)
              .message("Wallet not found")
              .build();
   }

   public void deleteWallet(Integer id) {
      walletRepository.deleteById(id);
   }
}

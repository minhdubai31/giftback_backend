package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.WalletDto;
import com.minhdubai.Giftback.domain.entity.Wallet;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WalletMapper implements Mapper<Wallet, WalletDto> {

   private final ModelMapper modelMapper;

   @Override
   public WalletDto mapTo(Wallet wallet) {
      return modelMapper.map(wallet, WalletDto.class);
   }

   @Override
   public Wallet mapFrom(WalletDto walletDto) {
      return modelMapper.map(walletDto, Wallet.class);
   }
}

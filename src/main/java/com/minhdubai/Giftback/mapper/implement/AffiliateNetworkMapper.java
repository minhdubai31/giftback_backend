package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.AffiliateNetworkDto;
import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AffiliateNetworkMapper implements Mapper<AffiliateNetwork, AffiliateNetworkDto> {

   private final ModelMapper modelMapper;

   @Override
   public AffiliateNetworkDto mapTo(AffiliateNetwork affiliateNetwork) {
      return modelMapper.map(affiliateNetwork, AffiliateNetworkDto.class);
   }

   @Override
   public AffiliateNetwork mapFrom(AffiliateNetworkDto affiliateNetworkDto) {
      return modelMapper.map(affiliateNetworkDto, AffiliateNetwork.class);
   }
}

package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.PayoutDto;
import com.minhdubai.Giftback.domain.entity.Payout;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PayoutMapper implements Mapper<Payout, PayoutDto> {

   private final ModelMapper modelMapper;

   @Override
   public PayoutDto mapTo(Payout payout) {
      return modelMapper.map(payout, PayoutDto.class);
   }

   @Override
   public Payout mapFrom(PayoutDto payoutDto) {
      return modelMapper.map(payoutDto, Payout.class);
   }
}

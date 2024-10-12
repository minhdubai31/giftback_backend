package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.CashbackDto;
import com.minhdubai.Giftback.domain.entity.Cashback;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CashbackMapper implements Mapper<Cashback, CashbackDto> {

   private final ModelMapper modelMapper;

   @Override
   public CashbackDto mapTo(Cashback cashback) {
      return modelMapper.map(cashback, CashbackDto.class);
   }

   @Override
   public Cashback mapFrom(CashbackDto cashbackDto) {
      return modelMapper.map(cashbackDto, Cashback.class);
   }
}

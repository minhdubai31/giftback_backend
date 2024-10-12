package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.AffiliateProgramDto;
import com.minhdubai.Giftback.domain.entity.AffiliateProgram;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AffiliateProgramMapper implements Mapper<AffiliateProgram, AffiliateProgramDto> {

   private final ModelMapper modelMapper;

   @Override
   public AffiliateProgramDto mapTo(AffiliateProgram program) {
      return modelMapper.map(program, AffiliateProgramDto.class);
   }

   @Override
   public AffiliateProgram mapFrom(AffiliateProgramDto programDto) {
      return modelMapper.map(programDto, AffiliateProgram.class);
   }
}

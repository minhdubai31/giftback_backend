package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.ApiMapDto;
import com.minhdubai.Giftback.domain.entity.ApiMap;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApiMapMapper implements Mapper<ApiMap, ApiMapDto> {

   private final ModelMapper modelMapper;

   @Override
   public ApiMapDto mapTo(ApiMap apiMap) {
      return modelMapper.map(apiMap, ApiMapDto.class);
   }

   @Override
   public ApiMap mapFrom(ApiMapDto apiMapDto) {
      return modelMapper.map(apiMapDto, ApiMap.class);
   }
}

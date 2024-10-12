package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.BrandDto;
import com.minhdubai.Giftback.domain.entity.Brand;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BrandMapper implements Mapper<Brand, BrandDto> {

   private final ModelMapper modelMapper;

   @Override
   public BrandDto mapTo(Brand brand) {
      return modelMapper.map(brand, BrandDto.class);
   }

   @Override
   public Brand mapFrom(BrandDto brandDto) {
      return modelMapper.map(brandDto, Brand.class);
   }
}

package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Brand;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.BrandDto;
import com.minhdubai.Giftback.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
   private final BrandRepository brandRepository;
   private final Mapper<Brand, BrandDto> brandMapper;

   public ResponseDto createBrand(BrandDto brandDto) {
      Brand brand = Brand.builder()
            .name(brandDto.getName())
            .description(brandDto.getDescription())
            .logoPath(brandDto.getLogoPath())
            .build();
      Brand savedBrand = brandRepository.save(brand);
      BrandDto savedBrandDto = brandMapper.mapTo(savedBrand);
      return ResponseDto.builder()
            .status(201)
            .message("Brand created successfully")
            .data(savedBrandDto)
            .build();
   }

   public ResponseDto getAllBrands() {
      List<BrandDto> brandDtos = brandRepository.findAll()
            .stream()
            .map(brand -> brandMapper.mapTo(brand))
            .toList();
      return ResponseDto.builder()
            .status(200)
            .message("Brands retrieved successfully")
            .data(brandDtos)
            .build();
   }

   public ResponseDto getBrandById(Integer id) {
      Brand brand = brandRepository.findById(id).orElse(null);
      if (brand != null) {
         BrandDto brandDto = brandMapper.mapTo(brand);
         return ResponseDto.builder()
               .status(200)
               .message("Brand found")
               .data(brandDto)
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Brand not found")
            .build();
   }

   public ResponseDto updateBrand(Integer id, BrandDto brandDto) {
      Brand brand = brandRepository.findById(id).orElse(null);
      if (brand != null) {
         brand.setName(brandDto.getName());
         brand.setDescription(brandDto.getDescription());
         brand.setLogoPath(brandDto.getLogoPath());
         Brand savedBrand = brandRepository.save(brand);
         BrandDto savedBrandDto = brandMapper.mapTo(savedBrand);
         return ResponseDto.builder()
               .status(200)
               .message("Brand updated successfully")
               .data(savedBrandDto)
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Brand not found")
            .build();
   }

   public void deleteBrand(Integer id) {
      brandRepository.deleteById(id);
   }
}

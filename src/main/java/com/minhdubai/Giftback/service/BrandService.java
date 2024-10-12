package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Brand;
import com.minhdubai.Giftback.dto.BrandDTO;
import com.minhdubai.Giftback.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
   @Autowired
   private BrandRepository brandRepository;

   public Brand createBrand(BrandDTO brandDTO) {
      Brand brand = Brand.builder()
            .name(brandDTO.getName())
            .description(brandDTO.getDescription())
            .logoPath(brandDTO.getLogoPath())
            .build();
      return brandRepository.save(brand);
   }

   public List<Brand> getAllBrands() {
      return brandRepository.findAll();
   }

   public Brand getBrandById(Integer id) {
      return brandRepository.findById(id).orElse(null);
   }

   public Brand updateBrand(Integer id, BrandDTO brandDTO) {
      Brand brand = brandRepository.findById(id).orElse(null);
      if (brand != null) {
         brand.setName(brandDTO.getName());
         brand.setDescription(brandDTO.getDescription());
         brand.setLogoPath(brandDTO.getLogoPath());
         return brandRepository.save(brand);
      }
      return null;
   }

   public void deleteBrand(Integer id) {
      brandRepository.deleteById(id);
   }
}

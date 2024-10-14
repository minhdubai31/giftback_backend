package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.BrandDto;
import com.minhdubai.Giftback.service.BrandService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createBrand(@RequestBody BrandDto brandDto) {
        ResponseDto response = brandService.createBrand(brandDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllBrands() {
        ResponseDto response = brandService.getAllBrands();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getBrandById(@PathVariable Integer id) {
        ResponseDto response = brandService.getBrandById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateBrand(@PathVariable Integer id, @RequestBody BrandDto brandDto) {
        ResponseDto response = brandService.updateBrand(id, brandDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}

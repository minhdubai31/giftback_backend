package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.AffiliateNetworkDto;
import com.minhdubai.Giftback.service.AffiliateNetworkService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/networks")
public class AffiliateNetworkController {
    private final AffiliateNetworkService affiliateNetworkService;

    public AffiliateNetworkController(AffiliateNetworkService affiliateNetworkService) {
        this.affiliateNetworkService = affiliateNetworkService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAffiliateNetwork(@RequestBody AffiliateNetworkDto affiliateNetworkDto) {
        ResponseDto response = affiliateNetworkService.createAffiliateNetwork(affiliateNetworkDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllAffiliateNetworks() {
        ResponseDto response = affiliateNetworkService.getAllAffiliateNetworks();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAffiliateNetworkById(@PathVariable Integer id) {
        ResponseDto response = affiliateNetworkService.getAffiliateNetworkById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateAffiliateNetwork(@PathVariable Integer id, @RequestBody AffiliateNetworkDto affiliateNetworkDto) {
        ResponseDto response = affiliateNetworkService.updateAffiliateNetwork(id, affiliateNetworkDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffiliateNetwork(@PathVariable Integer id) {
        affiliateNetworkService.deleteAffiliateNetwork(id);
        return ResponseEntity.noContent().build();
    }
}

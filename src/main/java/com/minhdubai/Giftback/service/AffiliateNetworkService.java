package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import com.minhdubai.Giftback.domain.entity.ApiMap;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.AffiliateNetworkDto;
import com.minhdubai.Giftback.domain.dto.ApiMapDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.repository.AffiliateNetworkRepository;
import com.minhdubai.Giftback.repository.ApiMapRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AffiliateNetworkService {
   private final AffiliateNetworkRepository affiliateNetworkRepository;
   private final ApiMapRepository apiMapRepository;
   private final Mapper<AffiliateNetwork, AffiliateNetworkDto> networkMapper;
   private final Mapper<ApiMap, ApiMapDto> apiMapper;

   public ResponseDto createAffiliateNetwork(AffiliateNetworkDto affiliateNetworkDto) {
      AffiliateNetwork affiliateNetwork = networkMapper.mapFrom(affiliateNetworkDto);

      if (affiliateNetworkDto.getApiMap() != null) {
         var apiMapDto = affiliateNetworkDto.getApiMap();
         ApiMap apiMap = ApiMap.builder()
               .getCampaignApi(apiMapDto.getGetCampaignApi())
               .getTransactionApi(apiMapDto.getGetTransactionApi())
               .getCampaignCommissionApi(apiMapDto.getGetCampaignCommissionApi())
               .build();
         ApiMap savedApiMap = apiMapRepository.save(apiMap);
         affiliateNetwork.setApiMap(savedApiMap);
      } else {
         affiliateNetwork.setApiMap(null);
      }

      AffiliateNetwork savedNetwork = affiliateNetworkRepository.save(affiliateNetwork);
      return ResponseDto.builder()
            .status(201)
            .message("Affiliate Network created successfully")
            .data(networkMapper.mapTo(savedNetwork))
            .build();
   }

   public ResponseDto getAllAffiliateNetworks() {
      List<AffiliateNetwork> networks = affiliateNetworkRepository.findAll();
      List<AffiliateNetworkDto> networkDtos = networks.stream()
            .map(networkMapper::mapTo)
            .toList();
      return ResponseDto.builder()
            .status(200)
            .message("Affiliate Networks retrieved successfully")
            .data(networkDtos)
            .build();
   }

   public ResponseDto getAffiliateNetworkById(Integer id) {
      AffiliateNetwork network = affiliateNetworkRepository.findById(id).orElse(null);
      if (network != null) {
         return ResponseDto.builder()
               .status(200)
               .message("Affiliate Network found")
               .data(networkMapper.mapTo(network))
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Affiliate Network not found")
            .build();
   }

   public ResponseDto updateAffiliateNetwork(Integer id, AffiliateNetworkDto affiliateNetworkDto) {
      AffiliateNetwork existingNetwork = affiliateNetworkRepository.findById(id).orElse(null);
      if (existingNetwork != null) {
         existingNetwork.setName(affiliateNetworkDto.getName());
         existingNetwork.setUrl(affiliateNetworkDto.getUrl());
         existingNetwork.setAuthorizeToken(affiliateNetworkDto.getAuthorizeToken());
         existingNetwork.setApiMap(apiMapRepository.save(apiMapper.mapFrom(affiliateNetworkDto.getApiMap())));
         AffiliateNetwork savedNetwork = affiliateNetworkRepository.save(existingNetwork);
         return ResponseDto.builder()
               .status(200)
               .message("Affiliate Network updated successfully")
               .data(networkMapper.mapTo(savedNetwork))
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Affiliate Network not found")
            .build();
   }

   public void deleteAffiliateNetwork(Integer id) {
      affiliateNetworkRepository.deleteById(id);
   }
}

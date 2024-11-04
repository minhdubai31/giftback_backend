package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.domain.entity.ApiMap;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.ApiMapDto;
import com.minhdubai.Giftback.repository.ApiMapRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiMapService {
   private ApiMapRepository apiMapRepository;
   private Mapper<ApiMap, ApiMapDto> apiMapMapper;

   public ResponseDto createApiMap(ApiMapDto apiMapDto) {
      ApiMap apiMap = ApiMap.builder()
            .getCampaignApi(apiMapDto.getGetCampaignApi())
            .getTransactionApi(apiMapDto.getGetTransactionApi())
            .build();
      ApiMap savedApiMap = apiMapRepository.save(apiMap);
      return ResponseDto.builder()
            .status(201)
            .message("API Map created successfully")
            .data(savedApiMap)
            .build();
   }

   public ResponseDto getAllApiMaps() {
      List<ApiMapDto> apiMapDtos = apiMapRepository.findAll().stream()
            .map(apiMap -> apiMapMapper.mapTo(apiMap))
            .toList();
      return ResponseDto.builder()
            .status(200)
            .message("API Maps retrieved successfully")
            .data(apiMapDtos)
            .build();
   }

   public ResponseDto getApiMapById(Integer id) {
      ApiMap apiMap = apiMapRepository.findById(id).orElse(null);
      if (apiMap != null) {
         return ResponseDto.builder()
               .status(200)
               .message("API Map found")
               .data(apiMap)
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("API Map not found")
            .build();
   }

   public ApiMap updateApiMap(Integer id, ApiMapDto apiMapDto) {
      ApiMap apiMap = apiMapRepository.findById(id).orElse(null);
      if (apiMap != null) {
         apiMap.setGetCampaignApi(apiMapDto.getGetCampaignApi());
         apiMap.setGetTransactionApi(apiMapDto.getGetTransactionApi());
         return apiMapRepository.save(apiMap);
      }
      return null;
   }

   public void deleteApiMap(Integer id) {
      apiMapRepository.deleteById(id);
   }
}

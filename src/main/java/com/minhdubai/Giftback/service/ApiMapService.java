package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.ApiMap;
import com.minhdubai.Giftback.dto.ApiMapDTO;
import com.minhdubai.Giftback.repository.ApiMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiMapService {
   @Autowired
   private ApiMapRepository apiMapRepository;

   public ApiMap createApiMap(ApiMapDTO apiMapDTO) {
      ApiMap apiMap = ApiMap.builder()
            .getCampaignApi(apiMapDTO.getGetCampaignApi())
            .getProductApi(apiMapDTO.getGetProductApi())
            .getTransactionApi(apiMapDTO.getGetTransactionApi())
            .build();
      return apiMapRepository.save(apiMap);
   }

   public List<ApiMap> getAllApiMaps() {
      return apiMapRepository.findAll();
   }

   public ApiMap getApiMapById(Integer id) {
      return apiMapRepository.findById(id).orElse(null);
   }

   public ApiMap updateApiMap(Integer id, ApiMapDTO apiMapDTO) {
      ApiMap apiMap = apiMapRepository.findById(id).orElse(null);
      if (apiMap != null) {
         apiMap.setGetCampaignApi(apiMapDTO.getGetCampaignApi());
         apiMap.setGetProductApi(apiMapDTO.getGetProductApi());
         apiMap.setGetTransactionApi(apiMapDTO.getGetTransactionApi());
         return apiMapRepository.save(apiMap);
      }
      return null;
   }

   public void deleteApiMap(Integer id) {
      apiMapRepository.deleteById(id);
   }
}

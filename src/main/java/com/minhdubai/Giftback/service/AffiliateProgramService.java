package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import com.minhdubai.Giftback.domain.entity.AffiliateProgram;
import com.minhdubai.Giftback.domain.entity.Brand;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.AffiliateNetworkDto;
import com.minhdubai.Giftback.domain.dto.AffiliateProgramDto;
import com.minhdubai.Giftback.domain.dto.BrandDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.repository.AffiliateNetworkRepository;
import com.minhdubai.Giftback.repository.AffiliateProgramRepository;
import com.minhdubai.Giftback.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AffiliateProgramService {
    private final AffiliateProgramRepository affiliateProgramRepository;
    private final AffiliateNetworkRepository affiliateNetworkRepository;
    private final BrandRepository brandRepository;
    private final Mapper<Brand, BrandDto> brandMapper;
    private final Mapper<AffiliateProgram, AffiliateProgramDto> programMapper;
    private final Mapper<AffiliateNetwork, AffiliateNetworkDto> networkMapper;

    public ResponseDto createAffiliateProgram(AffiliateProgramDto affiliateProgramDto) {
        AffiliateProgram affiliateProgram = programMapper.mapFrom(affiliateProgramDto);
        AffiliateProgram savedProgram = affiliateProgramRepository.save(affiliateProgram);
        return ResponseDto.builder()
                .status(201)
                .message("Affiliate Program created successfully")
                .data(programMapper.mapTo(savedProgram))
                .build();
    }

    public ResponseDto getAllAffiliatePrograms() {
        List<AffiliateProgram> programs = affiliateProgramRepository.findAll();
        List<AffiliateProgramDto> programDtos = programs.stream()
                .map(programMapper::mapTo)
                .toList();
        return ResponseDto.builder()
                .status(200)
                .message("Affiliate Programs retrieved successfully")
                .data(programDtos)
                .build();
    }

    public ResponseDto getAffiliateProgramById(Integer id) {
        AffiliateProgram program = affiliateProgramRepository.findById(id).orElse(null);
        if (program != null) {
            return ResponseDto.builder()
                    .status(200)
                    .message("Affiliate Program found")
                    .data(programMapper.mapTo(program))
                    .build();
        }
        return ResponseDto.builder()
                .status(404)
                .message("Affiliate Program not found")
                .build();
    }

    public ResponseDto updateAffiliateProgram(Integer id, AffiliateProgramDto affiliateProgramDto) {
        AffiliateProgram existingProgram = affiliateProgramRepository.findById(id).orElse(null);
        if (existingProgram != null) {
            existingProgram.setProgramName(affiliateProgramDto.getProgramName());
            existingProgram.setCommissionRate(affiliateProgramDto.getCommissionRate());
            existingProgram.setTerms(affiliateProgramDto.getTerms());
            existingProgram.setProgramUrl(affiliateProgramDto.getProgramUrl());
            existingProgram.setValidFrom(affiliateProgramDto.getValidFrom());
            existingProgram.setValidUntil(affiliateProgramDto.getValidUntil());
            AffiliateProgram savedProgram = affiliateProgramRepository.save(existingProgram);
            return ResponseDto.builder()
                    .status(200)
                    .message("Affiliate Program updated successfully")
                    .data(programMapper.mapTo(savedProgram))
                    .build();
        }
        return ResponseDto.builder()
                .status(404)
                .message("Affiliate Program not found")
                .build();
    }

    public void deleteAffiliateProgram(Integer id) {
        affiliateProgramRepository.deleteById(id);
    }

    public ResponseDto loadFromNetwork() {
        AffiliateNetwork network = affiliateNetworkRepository.findAll().getFirst();
        RestTemplate restTemplate = new RestTemplate();

        String url = network.getApiMap().getGetCampaignApi();
        String token = network.getAuthorizeToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + token);
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");
                List<AffiliateProgramDto> createdPrograms = data.stream()
                        .map(campaignData -> {
                            String merchantName = (String) campaignData.get("merchant");
                            Brand brand = brandRepository.findByName(merchantName)
                                    .orElseGet(() -> {
                                        Brand newBrand = Brand.builder()
                                                .name(merchantName)
                                                .build();
                                        return brandRepository.save(newBrand);
                                    });

                            
                            String commissionRate = ((String) ((Map<String, Object>)campaignData.get("description")).get("commission_policy"))
                                    .trim();
                            System.out.println("Commission Rate: " + commissionRate);
                            
                            AffiliateProgramDto dto = AffiliateProgramDto.builder()
                                    .programName((String) campaignData.get("name"))
                                    .terms((String) campaignData.get("cookie_policy"))
                                    .programUrl((String) campaignData.get("url"))
                                    .logo((String) campaignData.get("logo"))
                                    .campaignId((String) campaignData.get("id"))
                                    .brand(brandMapper.mapTo(brand))
                                    .affiliateNetwork(networkMapper.mapTo(network))
                                    .commissionRate(commissionRate)
                                    .build();

                            // Parse dates
                            String startTime = (String) campaignData.get("start_time");
                            String endTime = (String) campaignData.get("end_time");
                            if (startTime != null) {
                                dto.setValidFrom(LocalDateTime.parse(startTime));
                            }
                            if (endTime != null) {
                                dto.setValidUntil(LocalDateTime.parse(endTime));
                            }

                            AffiliateProgram existingProgram = affiliateProgramRepository
                                    .findByCampaignId(dto.getCampaignId())
                                    .orElse(null);
                            if (existingProgram == null) {
                                existingProgram = affiliateProgramRepository.save(programMapper.mapFrom(dto));
                            } 

                            return dto;
                        })
                        .toList();

                return ResponseDto.builder()
                        .status(200)
                        .message("Retrieved programs from network")
                        .data(createdPrograms)
                        .build();
            }

            return ResponseDto.builder()
                    .status(400)
                    .message("Failed to retrieve programs from network")
                    .build();

        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseDto.builder()
                    .status(500)
                    .message("Error retrieving programs from network: " + e.getMessage())
                    .build();
        }
    }
}

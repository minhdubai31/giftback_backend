package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.AffiliateProgram;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.AffiliateProgramDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import com.minhdubai.Giftback.repository.AffiliateProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliateProgramService {
    private final AffiliateProgramRepository affiliateProgramRepository;
    private final Mapper<AffiliateProgram, AffiliateProgramDto> programMapper;

    public AffiliateProgramService(AffiliateProgramRepository affiliateProgramRepository, 
                                   Mapper<AffiliateProgram, AffiliateProgramDto> programMapper) {
        this.affiliateProgramRepository = affiliateProgramRepository;
        this.programMapper = programMapper;
    }

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
}

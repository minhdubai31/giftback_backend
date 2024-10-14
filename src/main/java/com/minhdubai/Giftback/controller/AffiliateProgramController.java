package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.AffiliateProgramDto;
import com.minhdubai.Giftback.service.AffiliateProgramService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
public class AffiliateProgramController {
    private final AffiliateProgramService affiliateProgramService;

    public AffiliateProgramController(AffiliateProgramService affiliateProgramService) {
        this.affiliateProgramService = affiliateProgramService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAffiliateProgram(@RequestBody AffiliateProgramDto affiliateProgramDto) {
        ResponseDto response = affiliateProgramService.createAffiliateProgram(affiliateProgramDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllAffiliatePrograms() {
        ResponseDto response = affiliateProgramService.getAllAffiliatePrograms();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAffiliateProgramById(@PathVariable Integer id) {
        ResponseDto response = affiliateProgramService.getAffiliateProgramById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateAffiliateProgram(@PathVariable Integer id, @RequestBody AffiliateProgramDto affiliateProgramDto) {
        ResponseDto response = affiliateProgramService.updateAffiliateProgram(id, affiliateProgramDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffiliateProgram(@PathVariable Integer id) {
        affiliateProgramService.deleteAffiliateProgram(id);
        return ResponseEntity.noContent().build();
    }
}

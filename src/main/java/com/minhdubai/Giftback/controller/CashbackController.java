package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.CashbackDto;
import com.minhdubai.Giftback.service.CashbackService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cashbacks")
public class CashbackController {
    private final CashbackService cashbackService;

    public CashbackController(CashbackService cashbackService) {
        this.cashbackService = cashbackService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createCashback(@RequestBody CashbackDto cashbackDto) {
        ResponseDto response = cashbackService.createCashback(cashbackDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllCashbacks() {
        ResponseDto response = cashbackService.getAllCashbacks();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCashbackById(@PathVariable Integer id) {
        ResponseDto response = cashbackService.getCashbackById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateCashback(@RequestBody CashbackDto cashbackDto) {
        ResponseDto response = cashbackService.updateCashback(cashbackDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCashback(@PathVariable Integer id) {
        cashbackService.deleteCashback(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/load-from-network")
    public ResponseEntity<ResponseDto> updateCashbackFromNetwork() {
        ResponseDto response = cashbackService.updateCashbackFromNetwork();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

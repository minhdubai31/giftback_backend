package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.PayoutDto;
import com.minhdubai.Giftback.service.PayoutService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payouts")
public class PayoutController {
    private final PayoutService payoutService;

    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createPayout(@RequestBody PayoutDto payoutDto) {
        ResponseDto response = payoutService.createPayout(payoutDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllPayouts() {
        ResponseDto response = payoutService.getAllPayouts();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getPayoutById(@PathVariable Integer id) {
        ResponseDto response = payoutService.getPayoutById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updatePayout(@PathVariable Integer id, @RequestBody PayoutDto payoutDto) {
        ResponseDto response = payoutService.updatePayout(id, payoutDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayout(@PathVariable Integer id) {
        payoutService.deletePayout(id);
        return ResponseEntity.noContent().build();
    }
}

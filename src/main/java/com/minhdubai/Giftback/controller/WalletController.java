package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.WalletDto;
import com.minhdubai.Giftback.service.WalletService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createWallet(@RequestBody WalletDto walletDto) {
        ResponseDto response = walletService.createWallet(walletDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllWallets() {
        ResponseDto response = walletService.getAllWallets();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getWalletById(@PathVariable Integer id) {
        ResponseDto response = walletService.getWalletById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateWallet(@PathVariable Integer id, @RequestBody WalletDto walletDto) {
        ResponseDto response = walletService.updateWallet(id, walletDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Integer id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}

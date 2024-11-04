package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.domain.dto.TransactionDto;
import com.minhdubai.Giftback.service.TransactionService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        ResponseDto response = transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllTransactions() {
        ResponseDto response = transactionService.getAllTransactions();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getTransactionById(@PathVariable Integer id) {
        ResponseDto response = transactionService.getTransactionById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateTransaction(@RequestBody TransactionDto transactionDto) {
        ResponseDto response = transactionService.updateTransaction(transactionDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/update-from-network")
    public ResponseEntity<ResponseDto> updateTransactionFromNetwork() {
        ResponseDto response = transactionService.updateTransactionFromNetwork();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

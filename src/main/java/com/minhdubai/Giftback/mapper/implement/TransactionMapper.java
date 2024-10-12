package com.minhdubai.Giftback.mapper.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.domain.dto.TransactionDto;
import com.minhdubai.Giftback.domain.entity.Transaction;
import com.minhdubai.Giftback.mapper.Mapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransactionMapper implements Mapper<Transaction, TransactionDto> {

   private final ModelMapper modelMapper;

   @Override
   public TransactionDto mapTo(Transaction transaction) {
      return modelMapper.map(transaction, TransactionDto.class);
   }

   @Override
   public Transaction mapFrom(TransactionDto transactionDto) {
      return modelMapper.map(transactionDto, Transaction.class);
   }
}

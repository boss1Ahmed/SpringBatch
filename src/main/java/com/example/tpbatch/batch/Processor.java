package com.example.tpbatch.batch;

import com.example.tpbatch.dto.CompteTransactionDto;
import com.example.tpbatch.mapper.ICompteTransactionMapper;
import com.example.tpbatch.model.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<CompteTransactionDto, Transaction> {
    @Autowired
    private ICompteTransactionMapper transactionMapper;
    @Override
    public Transaction process(CompteTransactionDto compteTransactionDto) throws Exception {
        return transactionMapper.dtoToDomain(compteTransactionDto);
    }
}

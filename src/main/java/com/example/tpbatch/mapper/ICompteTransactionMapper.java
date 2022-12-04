package com.example.tpbatch.mapper;

import com.example.tpbatch.dto.CompteTransactionDto;
import com.example.tpbatch.model.Transaction;

public interface ICompteTransactionMapper {
    Transaction dtoToDomain(CompteTransactionDto compteTransactionDto);
    CompteTransactionDto domainToDto(Transaction transaction);
}

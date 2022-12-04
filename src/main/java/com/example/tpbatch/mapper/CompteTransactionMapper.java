package com.example.tpbatch.mapper;

import com.example.tpbatch.dto.CompteTransactionDto;
import com.example.tpbatch.model.Compte;
import com.example.tpbatch.model.Transaction;
import com.example.tpbatch.reposiory.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteTransactionMapper implements ICompteTransactionMapper{
    @Autowired
    private CompteRepository compteRepository;

    @Override
    public Transaction dtoToDomain(CompteTransactionDto compteTransactionDto) {
        Compte compte = compteRepository.findById(compteTransactionDto.getIdCompte()).get();
        return Transaction.builder()
                .dateTransaction(compteTransactionDto.getDateTransaction())
                .montant(compteTransactionDto.getMontant())
                .compte(compte)
                .build();
    }

    @Override
    public CompteTransactionDto domainToDto(Transaction transaction) {
        return null;
    }
}

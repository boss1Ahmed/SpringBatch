package com.example.tpbatch.batch;


import com.example.tpbatch.model.Compte;
import com.example.tpbatch.model.Transaction;
import com.example.tpbatch.reposiory.CompteRepository;
import com.example.tpbatch.reposiory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
@Service
public class JobService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CompteRepository compteRepository;
    public void debiter(){
        Date date=new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setMonth(date.getMonth()-1);
        List<Transaction> transactions = transactionRepository.findTransactionsByDateTransactionAfter(date);
        transactions.forEach(transaction -> {
            double montant=transaction.getMontant();
            Compte account=transaction.getCompte();
            account.setSolde(account.getSolde()-montant);
            compteRepository.save(account);
        });

    }



}


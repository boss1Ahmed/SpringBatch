package com.example.tpbatch.reposiory;

import com.example.tpbatch.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTransactionsByDateTransactionAfter(Date date);
}

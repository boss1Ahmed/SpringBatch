package com.example.tpbatch.batch;

import com.example.tpbatch.model.Transaction;
import com.example.tpbatch.reposiory.TransactionRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<Transaction>
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void write(Chunk<? extends Transaction> transactions) throws Exception {
        transactionRepository.saveAll(transactions);
    }
}

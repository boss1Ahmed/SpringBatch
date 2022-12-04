package com.example.tpbatch.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompteTransactionDto {
    private long idTransaction;
    private long idCompte;
    private double montant;
    private Date dateTransaction;
}

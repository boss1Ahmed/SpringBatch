package com.example.tpbatch.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


import java.util.Date;

@Entity
@Builder
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTransaction;

    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;

    @ManyToOne(targetEntity = Compte.class)
    @JoinColumn(name = "idCompte")
    private Compte compte;

}

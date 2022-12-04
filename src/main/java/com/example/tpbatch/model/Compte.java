package com.example.tpbatch.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCompte;

    private double solde;



}

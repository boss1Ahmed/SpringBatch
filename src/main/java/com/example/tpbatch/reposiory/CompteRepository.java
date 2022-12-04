package com.example.tpbatch.reposiory;

import com.example.tpbatch.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {
}

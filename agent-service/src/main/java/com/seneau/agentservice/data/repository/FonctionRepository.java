package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.Fonction;
import com.seneau.communs.core.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionRepository extends GenericRepository<Fonction> {
    Fonction findByNameAndActiveTrue(String name);
}

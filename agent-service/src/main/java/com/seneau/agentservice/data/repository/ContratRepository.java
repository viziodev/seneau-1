package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}

package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findAllByActiveTrue();
    Page<Agent> findAllByActiveTrue(Pageable pageable);
}

package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.ApplicationAccessAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAccessAgentRepository extends JpaRepository<ApplicationAccessAgent, Long> {
}

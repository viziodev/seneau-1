package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}

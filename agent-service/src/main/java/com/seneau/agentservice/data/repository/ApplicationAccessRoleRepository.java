package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.ApplicationAccessRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAccessRoleRepository extends JpaRepository<ApplicationAccessRole, Long> {
}

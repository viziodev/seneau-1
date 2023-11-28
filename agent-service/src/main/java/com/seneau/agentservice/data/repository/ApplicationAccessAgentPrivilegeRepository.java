package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.ApplicationAccessAgentPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationAccessAgentPrivilegeRepository extends JpaRepository<ApplicationAccessAgentPrivilege, Long> {
    @Query("SELECT ac FROM ApplicationAccessAgentPrivilege ac WHERE ac.active=true AND ac.applicationAccessAgent.agent.id=:agentId")
    List<ApplicationAccessAgentPrivilege> findAllByAgentId(@Param("agentId") Long agentId);
}

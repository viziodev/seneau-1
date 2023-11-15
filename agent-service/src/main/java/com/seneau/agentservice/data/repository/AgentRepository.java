package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findAllByActiveTrue();
    List<Agent> findByMatriculeInAndActiveTrue(List<Integer> matricules);
    Page<Agent> findAllByActiveTrue(Pageable pageable);
    Agent findByMatriculeAndActiveTrue(Integer matricule);
    @Query("SELECT a FROM Agent a WHERE a.active=true AND a.chef.matricule=:matricule")
    List<Agent> findAllByChefMatriculeAndActiveTrue(@Param("matricule") Integer matricule);
    @Query("SELECT a FROM Agent a WHERE a.active=true AND a.direction.chef.matricule=:matricule")
    List<Agent> findAllByDirecteurMatriculeAndActiveTrue(@Param("matricule") Integer matricule);
    @Query("SELECT a FROM Agent a WHERE a.active=true AND a.etablissement.id = :etablissementId")
    List<Agent> findAllByEtablissementAndActiveTrue(@Param("etablissementId") Long etablissementId);
    @Query("SELECT a FROM Agent a WHERE a.active=true AND a.direction.id = :directionId")
    List<Agent> findAllByDirectionAndActiveTrue(@Param("directionId") Long directionId);
}

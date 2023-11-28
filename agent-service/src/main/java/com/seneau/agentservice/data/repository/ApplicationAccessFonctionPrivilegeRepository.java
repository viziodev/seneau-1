package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.ApplicationAccessFonctionPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationAccessFonctionPrivilegeRepository extends JpaRepository<ApplicationAccessFonctionPrivilege, Long> {
    @Query("SELECT ac FROM ApplicationAccessFonctionPrivilege ac WHERE ac.active=true AND ac.applicationAccessFonction.fonction.id=:fonctionId")
    List<ApplicationAccessFonctionPrivilege> findAllByFonctionId(@Param("fonctionId") Long fonctionId);
}

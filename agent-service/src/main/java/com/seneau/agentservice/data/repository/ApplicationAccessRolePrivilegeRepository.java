package com.seneau.agentservice.data.repository;

import com.seneau.agentservice.data.model.ApplicationAccessRolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationAccessRolePrivilegeRepository extends JpaRepository<ApplicationAccessRolePrivilege, Long> {
    @Query("SELECT ac FROM ApplicationAccessRolePrivilege ac WHERE ac.active=true AND ac.applicationAccessRole.role.id=:roleId")
    List<ApplicationAccessRolePrivilege> findAllByRoleId(@Param("roleId") Long roleId);
}

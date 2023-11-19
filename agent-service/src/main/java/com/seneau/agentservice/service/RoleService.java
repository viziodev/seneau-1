package com.seneau.agentservice.service;

import com.seneau.agentservice.data.model.Role;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;
import com.seneau.communs.data.dto.role.RoleDto;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);
    ApplicationAccessDto createApplicationAccess(ApplicationAccessRequestDto applicationAccessRequestDto);
    RoleDto getRoleById(Long id);
}

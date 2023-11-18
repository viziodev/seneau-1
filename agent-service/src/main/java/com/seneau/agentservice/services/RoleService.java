package com.seneau.agentservice.services;

import com.seneau.agentservice.data.model.Role;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);
    ApplicationAccessDto createApplicationAccess(ApplicationAccessRequestDto applicationAccessRequestDto);
    Role getRoleById(Long id);
}

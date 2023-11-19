package com.seneau.agentservice.web.controller.implement;

import com.seneau.agentservice.service.RoleService;
import com.seneau.agentservice.web.controller.RoleController;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleControllerImplement implements RoleController {
    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleResponseDto> createRole(RoleRequestDto roleRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApplicationAccessDto> createApplicationAccess(ApplicationAccessRequestDto applicationAccessRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createApplicationAccess(applicationAccessRequestDto));
    }
}

package com.seneau.agentservice.web.controller;

import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;
import com.seneau.communs.data.dto.role.RoleDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleController {
    @PostMapping("/roles")
    ResponseEntity<RoleResponseDto> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto);
    @GetMapping("/role/{id}")
    ResponseEntity<RoleDto> getRoleById(@PathVariable Long id);
    @PostMapping("/application-access")
    ResponseEntity<ApplicationAccessDto> createApplicationAccess(@Valid @RequestBody ApplicationAccessRequestDto applicationAccessRequestDto);
}

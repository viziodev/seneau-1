package com.seneau.agentservice.web.controller;

import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleController {
    @PostMapping("/roles")
    ResponseEntity<RoleResponseDto> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto);
    @PostMapping("/application-access")
    ResponseEntity<ApplicationAccessDto> createApplicationAccess(@Valid @RequestBody ApplicationAccessRequestDto applicationAccessRequestDto);
}

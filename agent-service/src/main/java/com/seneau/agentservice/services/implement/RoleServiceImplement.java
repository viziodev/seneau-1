package com.seneau.agentservice.services.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.Agent;
import com.seneau.agentservice.data.model.Application;
import com.seneau.agentservice.data.model.ApplicationAccess;
import com.seneau.agentservice.data.model.Role;
import com.seneau.agentservice.data.repository.AgentRepository;
import com.seneau.agentservice.data.repository.ApplicationAccessRepository;
import com.seneau.agentservice.data.repository.ApplicationRepository;
import com.seneau.agentservice.data.repository.RoleRepository;
import com.seneau.agentservice.services.RoleService;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessDto;
import com.seneau.agentservice.web.controller.dto.ApplicationAccessRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleRequestDto;
import com.seneau.agentservice.web.controller.dto.RoleResponseDto;
import com.seneau.agentservice.web.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImplement implements RoleService {
    private final RoleRepository roleRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationAccessRepository applicationAccessRepository;
    private final AgentRepository agentRepository;
    private final ObjectMapper objectMapper;
    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        return null;
    }

    @Override
    public ApplicationAccessDto createApplicationAccess(ApplicationAccessRequestDto applicationAccessRequestDto) {
        Role role = getRoleById(applicationAccessRequestDto.getRole());
        if (role == null) throw new EntityNotFoundException("role not found with provided id");
        Application application = applicationRepository.findById(applicationAccessRequestDto.getApplication()).orElse(null);
        if (application == null) throw new EntityNotFoundException("application not found with provided id");
        Agent agent = agentRepository.findById(applicationAccessRequestDto.getAgent()).orElse(null);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided id");
        ApplicationAccess applicationAccess = new ApplicationAccess();
        applicationAccess.setApplication(application);
        applicationAccess.setRole(role);
        applicationAccess.setAgent(agent);
        return objectMapper.convertValue(applicationAccessRepository.save(applicationAccess), ApplicationAccessDto.class);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}

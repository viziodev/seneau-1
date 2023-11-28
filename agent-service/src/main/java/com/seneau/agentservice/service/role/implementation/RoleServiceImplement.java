package com.seneau.agentservice.service.role.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.Agent;
import com.seneau.agentservice.data.model.Application;
import com.seneau.agentservice.data.model.ApplicationAccessAgent;
import com.seneau.agentservice.data.model.Role;
import com.seneau.agentservice.data.repository.*;
import com.seneau.agentservice.service.role.RoleService;
import com.seneau.agentservice.web.dto.*;
import com.seneau.communs.web.exceptions.EntityNotFoundException;
import com.seneau.communs.data.dto.role.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImplement implements RoleService {
    private final RoleRepository roleRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationAccessAgentRepository applicationAccessAgentRepository;
    private final AgentRepository agentRepository;
    private final ObjectMapper objectMapper;
    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Role role = new Role();
        role.setName(roleRequestDto.getName());
        role.setCode(roleRequestDto.getCode());
        if (roleRequestDto.getRoleHierarchique() != null) {
            Role roleHierarchique = roleRepository.findById(roleRequestDto.getRoleHierarchique()).orElse(null);
            role.setRolesHierarchique(roleHierarchique);
        }
        if (roleRequestDto.getRoleFonctionnel() != null) {
            Role roleFonctionnel = roleRepository.findById(roleRequestDto.getRoleFonctionnel()).orElse(null);
            role.setRolesFonctionnel(roleFonctionnel);
        }
        role = roleRepository.save(role);
        List<ApplicationAccessAgent> applicationAccessAgents = new ArrayList<>();
        for (Long app : roleRequestDto.getApplications()) {
            Application application = applicationRepository.findById(app).orElse(null);
            if (application != null) {
                ApplicationAccessAgent applicationAccessAgent = new ApplicationAccessAgent();
                // applicationAccess.setRole(role);
                applicationAccessAgent.setApplication(application);
                applicationAccessAgents.add(applicationAccessAgent);
            }
        }
        applicationAccessAgentRepository.saveAll(applicationAccessAgents);
        return objectMapper.convertValue(role, RoleResponseDto.class);
    }

    @Override
    public ApplicationAccessDto createApplicationAccess(ApplicationAccessRequestDto applicationAccessRequestDto) {
        Role role = objectMapper.convertValue(getRoleById(applicationAccessRequestDto.getRole()), Role.class);
        if (role == null) throw new EntityNotFoundException("role not found with provided id");
        Application application = applicationRepository.findById(applicationAccessRequestDto.getApplication()).orElse(null);
        if (application == null) throw new EntityNotFoundException("application not found with provided id");
        Agent agent = agentRepository.findById(applicationAccessRequestDto.getAgent()).orElse(null);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided id");
        ApplicationAccessAgent applicationAccessAgent = new ApplicationAccessAgent();
        applicationAccessAgent.setApplication(application);
        //applicationAccess.setRole(role);
        applicationAccessAgent.setAgent(agent);
        return objectMapper.convertValue(applicationAccessAgentRepository.save(applicationAccessAgent), ApplicationAccessDto.class);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return objectMapper.convertValue(role, RoleDto.class);
    }

    @Override
    public List<RoleHierarchiqueDto> getListRoleHierarchiqueByRoleId(Long id) {
        List<RoleHierarchiqueDto> roleAgentDtos = new ArrayList<>(getInitialRole(id).stream().map(ag -> objectMapper.convertValue(ag, RoleHierarchiqueDto.class)).toList());
        RoleHierarchiqueDto roleAgentDto = roleAgentDtos.get(0);
        while (roleAgentDto.getRolesHierarchique() != null && roleAgentDto.getRolesHierarchique().getId() != null) {
            Role role = roleRepository.findById(roleAgentDto.getRolesHierarchique().getId()).orElse(null);
            if (role != null) {
                roleAgentDto = objectMapper.convertValue(role, RoleHierarchiqueDto.class);
                roleAgentDtos.add(roleAgentDto);
            }
        }
        return roleAgentDtos;
    }

    @Override
    public List<RoleFonctionnelDto> getListRoleFonctionnelByRoleId(Long id) {
        List<RoleFonctionnelDto> roleAgentDtos = new ArrayList<>(getInitialRole(id).stream().map(ag -> objectMapper.convertValue(ag, RoleFonctionnelDto.class)).toList());
        RoleFonctionnelDto roleAgentDto = roleAgentDtos.get(0);
        while (roleAgentDto.getRolesFonctionnel() != null && roleAgentDto.getRolesFonctionnel().getId() != null) {
            Role role = roleRepository.findById(roleAgentDto.getRolesFonctionnel().getId()).orElse(null);
            if (role != null) {
                roleAgentDto = objectMapper.convertValue(role, RoleFonctionnelDto.class);
                roleAgentDtos.add(roleAgentDto);
            }
        }
        return roleAgentDtos;
    }

    @Override
    public RoleHFDto getListRoleHierarchiqueFonctionnelByRoleId(Long id) {
        return new RoleHFDto(getListRoleHierarchiqueByRoleId(id), getListRoleFonctionnelByRoleId(id));
    }

    @Override
    public RoleApplicationAccessDto getRoleApplicationAccessList(Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) throw new EntityNotFoundException("role not found with the provided id");
        return objectMapper.convertValue(role, RoleApplicationAccessDto.class);
    }

    private List<RoleAgentDto> getInitialRole(Long id) {
        List<RoleAgentDto> roleAgentDtos = new ArrayList<>();
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) throw new EntityNotFoundException("role not found with the provided id");
        RoleAgentDto roleAgentDto = objectMapper.convertValue(role, RoleAgentDto.class);
        roleAgentDtos.add(roleAgentDto);
        return roleAgentDtos;
    }
}

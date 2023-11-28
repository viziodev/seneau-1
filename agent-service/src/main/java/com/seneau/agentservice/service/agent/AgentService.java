package com.seneau.agentservice.service.agent;

import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import com.seneau.agentservice.web.dto.CvDto;
import com.seneau.agentservice.web.dto.FilterDto;
import com.seneau.communs.data.dto.agent.AgentResponseDto;
import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AgentService {
    AgentResponse createAgent(AgentRequest agentRequest);
    List<AgentResponse> createAgentFromFileData(MultipartFile multipartFile, Integer numberOfSheet) throws IOException, ParseException;
    List<AgentResponse> getAllAgent();
    Map<String, Object> getAllAgentByFilterDto(FilterDto filterDto);
    List<AgentResponse> getAllAgentByMatriculeIn(List<Integer> matricules);
    List<AgentResponse> getAllAgentByMatriculeChef(Integer matricule);
    List<AgentResponse> getAllAgentByMatriculeDirecteur(Integer matricule);
    List<AgentResponse> getAllAgentByEtablissement(Long etablissementId);
    List<AgentResponse> getAllAgentByDirection(Long directionId);
    Map<String, Object> getAllAgent(int page, int size);
    AgentResponse getAgentByMatriculeAndActiveTrue(Integer matricule);
    AgentResponse disableAgent(Integer matricule);
    AgentResponseDto getAgentById(Long id);
    AgentResponseDto getChefByIdAgent(Long id);
    AgentResponse updateAgent(Long id, AgentRequest agentRequest);
    CvDto createCv(CvDto cvDto);
    List<PrivilegeResponseDto> getAllPrivilegeByAgentAndAgentRoleAndFonction(Long agent, Long role, Long fonction);
}

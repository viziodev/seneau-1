package com.seneau.agentservice.services;

import com.seneau.agentservice.data.model.*;
import com.seneau.agentservice.web.controller.dto.AgentRequest;
import com.seneau.agentservice.web.controller.dto.AgentResponse;
import com.seneau.agentservice.web.controller.dto.FilterDto;
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
    Agent getAgentById(Long id);
    AgentResponse updateAgent(Long id, AgentRequest agentRequest);
    Statut getStatutByNameAndActiveTrue(String name);
    Service getServiceByNameAndActiveTrue(String name);
    Etablissement getEtablissementByNameAndActiveTrue(String name);
    Fonction getFonctionByNameAndActiveTrue(String name);
    Contrat getContratByTyContratNameAndActiveTrue(String name);
    Direction getDirectionByNameAndActiveTrue(String name);
    TypeContrat getTypeContratByName(String name);
}

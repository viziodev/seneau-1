package com.seneau.agentservice.services;

import com.seneau.agentservice.data.model.*;
import com.seneau.agentservice.web.dto.AgentRequest;
import com.seneau.agentservice.web.dto.AgentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AgentService {
    AgentResponse createAgent(AgentRequest agentRequest);
    List<AgentResponse> createAgentFromFileData(MultipartFile multipartFile, Integer numberOfSheet) throws IOException, ParseException;
    List<AgentResponse> getAllAgent();
    Page<Agent> getAllAgent(Pageable pageable);
    Agent getAgentByMatriculeAndActiveTrue(Integer matricule);
    Statut getStatutByNameAndActiveTrue(String name);
    Service getServiceByNameAndActiveTrue(String name);
    Etablissement getEtablissementByNameAndActiveTrue(String name);
    Fonction getFonctionByNameAndActiveTrue(String name);
    Contrat getContratByTyContratNameAndActiveTrue(String name);
    Direction getDirectionByNameAndActiveTrue(String name);
    TypeContrat getTypeContratByName(String name);
}

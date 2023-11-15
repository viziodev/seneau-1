package com.seneau.agentservice.services.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.*;
import com.seneau.agentservice.data.repository.*;
import com.seneau.agentservice.services.AgentService;
import com.seneau.agentservice.services.utils.UploadService;
import com.seneau.agentservice.web.controller.dto.AgentRequest;
import com.seneau.agentservice.web.controller.dto.AgentResponse;
import com.seneau.agentservice.web.controller.dto.FileMapper;
import com.seneau.agentservice.web.controller.dto.PageListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgentServiceImplement implements AgentService {
    private final AgentRepository agentRepository;
    private final StatutRepository statutRepository;
    private final ServiceRepository serviceRepository;
    private final DirectionRepository directionRepository;
    private final ContratRepository contratRepository;
    private final FonctionRepository fonctionRepository;
    private final EtablissementRepository etablissementRepository;
    private final TypeContratRepository typeContratRepository;
    private final ObjectMapper objectMapper;
    private final UploadService uploadService;
    private final FileMapper fileMapper;
    private final PageListMapper pageListMapper;

    @Override
    public AgentResponse createAgent(AgentRequest agentRequest) {
        Agent agent = objectMapper.convertValue(agentRequest, Agent.class);
        return objectMapper.convertValue(agentRepository.save(agent), AgentResponse.class);
    }

    @Override
    public List<AgentResponse> createAgentFromFileData(MultipartFile multipartFile, Integer numberOfSheet) throws IOException, ParseException {
        List<Map<String, String>> fileData = uploadService.getDataFromExcelFile(multipartFile, numberOfSheet);
        List<Agent> agents = fileMapper.toAgent(fileData);
        for (Agent agent : agents) {
            Statut statut = statutRepository.findByNameAndActiveTrue(agent.getStatut().getName());
            if (statut != null) agent.setStatut(statut);
            com.seneau.agentservice.data.model.Service service = serviceRepository.findByNameAndActiveTrue(agent.getService().getName());
            if (service != null) agent.setService(service);
            Etablissement etablissement = etablissementRepository.findByNameAndActiveTrue(agent.getEtablissement().getName());
            if (etablissement != null) agent.setEtablissement(etablissement);
            Fonction fonction = fonctionRepository.findByNameAndActiveTrue(agent.getFonction().getName());
            if (fonction != null) agent.setFonction(fonction);
            Direction direction = directionRepository.findByNameAndActiveTrue(agent.getDirection().getName());
            if (direction != null) agent.setDirection(direction);
            Agent chef = agentRepository.findByMatriculeAndActiveTrue(agent.getChef().getMatricule());
            agent.setChef(chef);
        }

        agentRepository.saveAll(agents);
        return agents
                .stream()
                .map(data -> objectMapper.convertValue(data, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgent() {
        List<Agent> agents = agentRepository.findAllByActiveTrue();
        return agents
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeIn(List<Integer> matricules) {
        return agentRepository.findByMatriculeInAndActiveTrue(matricules)
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeChef(Integer matricule) {
        return agentRepository.findAllByChefMatriculeAndActiveTrue(matricule)
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeDirecteur(Integer matricule) {
        return agentRepository.findAllByDirecteurMatriculeAndActiveTrue(matricule)
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByEtablissement(Long etablissementId) {
        return agentRepository.findAllByEtablissementAndActiveTrue(etablissementId)
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByDirection(Long directionId) {
        return agentRepository.findAllByDirectionAndActiveTrue(directionId)
                .stream()
                .map(agent -> objectMapper.convertValue(agent, AgentResponse.class))
                .toList();
    }

    @Override
    public Map<String, Object> getAllAgent(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Agent> agentPage = agentRepository.findAllByActiveTrue(paging);
        List<AgentResponse> agentResponses = agentPage.getContent().stream().map(agent -> objectMapper.convertValue(agent, AgentResponse.class)).toList();
        return pageListMapper.getPageToMapObject(
                agentResponses,
                agentPage.getNumber(),
                agentPage.getTotalElements(),
                agentPage.getTotalPages()
        );
    }

    @Override
    public AgentResponse getAgentByMatriculeAndActiveTrue(Integer matricule) {
        return objectMapper.convertValue(agentRepository.findByMatriculeAndActiveTrue(matricule), AgentResponse.class);
    }

    @Override
    public AgentResponse disableAgent(Integer matricule) {
        Agent agent = agentRepository.findByMatriculeAndActiveTrue(matricule);
        if (agent == null) return null;
        agent.setActive(false);
        return objectMapper.convertValue(agentRepository.save(agent), AgentResponse.class);
    }

    @Override
    public Statut getStatutByNameAndActiveTrue(String name) {
        return statutRepository.findByNameAndActiveTrue(name);
    }

    @Override
    public com.seneau.agentservice.data.model.Service getServiceByNameAndActiveTrue(String name) {
        return serviceRepository.findByNameAndActiveTrue(name);
    }

    @Override
    public Etablissement getEtablissementByNameAndActiveTrue(String name) {
        return etablissementRepository.findByNameAndActiveTrue(name);
    }

    @Override
    public Fonction getFonctionByNameAndActiveTrue(String name) {
        return fonctionRepository.findByNameAndActiveTrue(name);
    }

    @Override
    public Contrat getContratByTyContratNameAndActiveTrue(String name) {
        return contratRepository.findByTypeContratNameAndActiveTrue(name);
    }

    @Override
    public Direction getDirectionByNameAndActiveTrue(String name) {
        return directionRepository.findByNameAndActiveTrue(name);
    }

    @Override
    public TypeContrat getTypeContratByName(String name) {
        return typeContratRepository.findByNameAndActiveTrue(name);
    }
}

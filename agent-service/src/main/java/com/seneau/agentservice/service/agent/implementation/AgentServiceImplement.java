package com.seneau.agentservice.service.agent.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.agentservice.data.model.*;
import com.seneau.agentservice.data.repository.*;
import com.seneau.agentservice.service.agent.AgentService;

import com.seneau.agentservice.web.dto.*;
import com.seneau.communs.data.dto.PageListMapper;
import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import com.seneau.communs.web.exceptions.BadRequestException;
import com.seneau.communs.web.exceptions.EntityNotFoundException;
import com.seneau.communs.data.dto.agent.AgentResponseDto;
import com.seneau.communs.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgentServiceImplement extends UploadService implements AgentService {
    private final AgentRepository agentRepository;
    private final StatutRepository statutRepository;
    private final ServiceRepository serviceRepository;
    private final DirectionRepository directionRepository;
    private final ContratRepository contratRepository;
    private final FonctionRepository fonctionRepository;
    private final EtablissementRepository etablissementRepository;
    private final TypeContratRepository typeContratRepository;
    private final CvRepository cvRepository;
    private final ProjetRepository projetRepository;
    private final CvProjetRepository cvProjetRepository;
    private final StatutFonctionRepository statutFonctionRepository;
    private final ApplicationAccessAgentPrivilegeRepository applicationAccessAgentPrivilegeRepository;
    private final ApplicationAccessRolePrivilegeRepository applicationAccessRolePrivilegeRepository;
    private final ApplicationAccessFonctionPrivilegeRepository applicationAccessFonctionPrivilegeRepository;
    private final ObjectMapper objectMapper;
    private final FileMapper fileMapper;

    private final PageListMapper pageListMapper;

    @Override
    public AgentResponse createAgent(AgentRequest agentRequest) {
        Agent agent = objectMapper.convertValue(agentRequest, Agent.class);
        return objectMapper.convertValue(agentRepository.save(agent), AgentResponse.class);
    }

    @Override
    public List<AgentResponse> createAgentFromFileData(MultipartFile multipartFile, Integer numberOfSheet) throws IOException, ParseException {
        List<Map<String, String>> fileData = super.getDataFromExcelFile(multipartFile, numberOfSheet);
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
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgent() {
        List<Agent> agents = agentRepository.findAllByActiveTrue();
        return agents
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public Map<String, Object> getAllAgentByFilterDto(FilterDto filterDto) {
        Pageable paging = PageRequest.of(filterDto.getPage(), filterDto.getPageSize(), Sort.by(filterDto.getSort()));
        Page<Agent> agentPage = agentRepository.findAllByFilterData(filterDto.getEtablissement(),
                filterDto.getDirection(), filterDto.getNom(), filterDto.getMatricule(), filterDto.getActive(), paging);
        List<AgentResponse> agentResponses = agentPage.getContent().stream().map(this::agentToAgentResponse).toList();
        return pageListMapper.getPageToMapObject(
                agentResponses,
                agentPage.getNumber(),
                agentPage.getTotalElements(),
                agentPage.getTotalPages()
        );
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeIn(List<Integer> matricules) {
        return agentRepository.findByMatriculeInAndActiveTrue(matricules)
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeChef(Integer matricule) {
        return agentRepository.findAllByChefMatriculeAndActiveTrue(matricule)
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByMatriculeDirecteur(Integer matricule) {
        return agentRepository.findAllByDirecteurMatriculeAndActiveTrue(matricule)
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByEtablissement(Long etablissementId) {
        return agentRepository.findAllByEtablissementAndActiveTrue(etablissementId)
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public List<AgentResponse> getAllAgentByDirection(Long directionId) {
        return agentRepository.findAllByDirectionAndActiveTrue(directionId)
                .stream()
                .map(this::agentToAgentResponse)
                .toList();
    }

    @Override
    public Map<String, Object> getAllAgent(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Agent> agentPage = agentRepository.findAllByActiveTrue(paging);
        List<AgentResponse> agentResponses = agentPage.getContent().stream().map(this::agentToAgentResponse).toList();
        return pageListMapper.getPageToMapObject(
                agentResponses,
                agentPage.getNumber(),
                agentPage.getTotalElements(),
                agentPage.getTotalPages()
        );
    }

    private AgentResponse agentToAgentResponse(Agent agent) {
        StatutFonction statutFonction = statutFonctionRepository.findByStatutAndFonctionAndActiveTrue(agent.getStatut(), agent.getFonction());
        AgentResponse agentResponse = objectMapper.convertValue(agent, AgentResponse.class);
        if (statutFonction != null) {
            agentResponse.getPrivileges().addAll(getAllPrivilegeByRole(statutFonction.getRole().getId()));
        }
        agentResponse.getPrivileges().addAll(getAllPrivilegeByAgent(agent.getId()));
        agentResponse.getPrivileges().addAll(getAllPrivilegeByFonction(agent.getFonction().getId()));
        return agentResponse;
    }

    private AgentResponseDto agentToAgentResponseDto(Agent agent) {
        StatutFonction statutFonction = statutFonctionRepository.findByStatutAndFonctionAndActiveTrue(agent.getStatut(), agent.getFonction());
        AgentResponseDto agentResponse = objectMapper.convertValue(agent, AgentResponseDto.class);
        if (statutFonction != null) {
            agentResponse.getPrivileges().addAll(getAllPrivilegeByRole(statutFonction.getRole().getId()));
        }
        agentResponse.getPrivileges().addAll(getAllPrivilegeByAgent(agent.getId()));
        agentResponse.getPrivileges().addAll(getAllPrivilegeByFonction(agent.getFonction().getId()));
        return agentResponse;
    }

    @Override
    public AgentResponse getAgentByMatriculeAndActiveTrue(Integer matricule) {
        Agent agent = agentRepository.findByMatriculeAndActiveTrue(matricule);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided matricule");
        StatutFonction statutFonction = statutFonctionRepository.findByStatutAndFonctionAndActiveTrue(agent.getStatut(), agent.getFonction());
        AgentResponse agentResponse = agentToAgentResponse(agent);
        if (statutFonction != null)
            agentResponse.setRole(objectMapper.convertValue(statutFonction.getRole(), RoleAgentDto.class));

        return agentResponse;
    }

    @Override
    public AgentResponse disableAgent(Integer matricule) {
        Agent agent = agentRepository.findByMatriculeAndActiveTrue(matricule);
        if (agent == null) return null;
        agent.setActive(false);
        return objectMapper.convertValue(agentRepository.save(agent), AgentResponse.class);
    }

     @Override
     public AgentResponseDto getAgentById(Long id) {
        Agent agent = agentRepository.findById(id).orElse(null);
        if (agent == null) return null;
        return agentToAgentResponseDto(agent);
     }

    @Override
    public AgentResponseDto getChefByIdAgent(Long id) {
        Agent agent = agentRepository.findById(id).orElse(null);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided id");
        if (agent.getChef() == null) throw new EntityNotFoundException("agent not found with provided id");
        return agentToAgentResponseDto(agent.getChef());
    }

    @Override
    public AgentResponse updateAgent(Long id, AgentRequest agentRequest) {
        Agent agent = agentRepository.findById(id).orElse(null);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided id");
        Agent agent1 = objectMapper.convertValue(agentRequest, Agent.class);
        BeanUtils.copyProperties(agent1, agent);
        agentRepository.save(agent);
        return agentToAgentResponse(agent);
    }

    @Override
    public CvDto createCv(CvDto cvDto) {
        if (cvDto == null) throw new BadRequestException("cv required");
        if (cvDto.getAgent() == null) throw new BadRequestException("agent required");
        Agent agent = agentRepository.findById(cvDto.getAgent()).orElse(null);
        if (agent == null) throw new EntityNotFoundException("agent not found with provided id");
        CV cv = new CV();
        cv.setAgent(agent);
        cv = cvRepository.save(cv);
        List<Projet> projets = cvDto.getProjets().
                stream()
                .map(projet -> objectMapper.convertValue(projet, Projet.class))
                .toList();
        projets = projetRepository.saveAll(projets);
        for (Projet projet : projets) {
            CVProjet cvProjet = new CVProjet();
            cvProjet.setCv(cv);
            cvProjet.setProjet(projet);
            cvProjetRepository.save(cvProjet);
        }
        return objectMapper.convertValue(cv, CvDto.class);
    }

    @Override
    public List<PrivilegeResponseDto> getAllPrivilegeByAgentAndAgentRoleAndFonction(Long agent, Long role, Long fonction) {
        List<Privilege> privileges = new ArrayList<>();
        privileges.addAll(applicationAccessAgentPrivilegeToPrivilege(applicationAccessAgentPrivilegeRepository.findAllByAgentId(agent)));
        privileges.addAll(applicationAccessRolePrivilegeToPrivilege(applicationAccessRolePrivilegeRepository.findAllByRoleId(role)));
        privileges.addAll(applicationAccessFonctionPrivilegeToPrivilege(applicationAccessFonctionPrivilegeRepository.findAllByFonctionId(fonction)));
        return privileges.stream().map(privilege -> objectMapper.convertValue(privilege, PrivilegeResponseDto.class)).toList();
    }

    private List<PrivilegeResponseDto> getAllPrivilegeByAgent(Long agent) {
        List<Privilege> privileges = new ArrayList<>(applicationAccessAgentPrivilegeToPrivilege(applicationAccessAgentPrivilegeRepository.findAllByAgentId(agent)));
        return privileges.stream().map(privilege -> objectMapper.convertValue(privilege, PrivilegeResponseDto.class)).toList();
    }
    private List<PrivilegeResponseDto> getAllPrivilegeByRole(Long role) {
        List<Privilege> privileges = new ArrayList<>(applicationAccessRolePrivilegeToPrivilege(applicationAccessRolePrivilegeRepository.findAllByRoleId(role)));
        return privileges.stream().map(privilege -> objectMapper.convertValue(privilege, PrivilegeResponseDto.class)).toList();
    }
    private List<PrivilegeResponseDto> getAllPrivilegeByFonction(Long fonction) {
        List<Privilege> privileges = new ArrayList<>(applicationAccessFonctionPrivilegeToPrivilege(applicationAccessFonctionPrivilegeRepository.findAllByFonctionId(fonction)));
        return privileges.stream().map(privilege -> objectMapper.convertValue(privilege, PrivilegeResponseDto.class)).toList();
    }

    private List<Privilege> applicationAccessAgentPrivilegeToPrivilege(List<ApplicationAccessAgentPrivilege> applicationAccessAgentPrivileges) {
        return applicationAccessAgentPrivileges.stream().map(ApplicationAccessAgentPrivilege::getPrivilege).toList();
    }

    private List<Privilege> applicationAccessRolePrivilegeToPrivilege(List<ApplicationAccessRolePrivilege> applicationAccessRolePrivileges) {
        return applicationAccessRolePrivileges.stream().map(ApplicationAccessRolePrivilege::getPrivilege).toList();
    }

    private List<Privilege> applicationAccessFonctionPrivilegeToPrivilege(List<ApplicationAccessFonctionPrivilege> applicationAccessFonctionPrivileges) {
        return applicationAccessFonctionPrivileges.stream().map(ApplicationAccessFonctionPrivilege::getPrivilege).toList();
    }

}

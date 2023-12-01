package com.seneau.demandeservice.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.communs.data.dto.PageListMapper;
import com.seneau.communs.data.dto.agent.AgentResponseDto;
import com.seneau.communs.data.dto.role.RoleDto;
import com.seneau.demandeservice.client.AgentRestClient;
import com.seneau.demandeservice.client.RoleRestClient;
import com.seneau.demandeservice.data.enumeration.EDemandeCongeStep;
import com.seneau.demandeservice.data.model.NiveauValidation;
import com.seneau.demandeservice.data.model.Validation;
import com.seneau.demandeservice.data.model.subclass.DemandeConge;
import com.seneau.demandeservice.data.repository.DemandeCongeRepository;
import com.seneau.demandeservice.data.repository.NiveauValidationRepository;
import com.seneau.demandeservice.data.repository.ValidationRepository;
import com.seneau.demandeservice.service.DemandeCongeService;
import com.seneau.demandeservice.service.design.conge.ServiceDemandeCongeWorkflow;
import com.seneau.demandeservice.web.dto.request.DemandeCongeRequestCreateDto;
import com.seneau.demandeservice.web.dto.response.DemandeCongeResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DemandeCongeServiceImpl implements DemandeCongeService {

    private final DemandeCongeRepository demandeCongeRepository;
    private final NiveauValidationRepository niveauValidationRepository;
    private final ValidationRepository validationRepository;
    private AgentRestClient agentRestClient;
    private RoleRestClient roleRestClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private final PageListMapper pageListMapper;
    @Override
    public DemandeCongeResponseDto goToNextStep(Long id, DemandeCongeRequestCreateDto request) {
        DemandeConge demandeConge = demandeCongeRepository.findById(id).orElseThrow();
         if(demandeConge.getCurrentStep()==EDemandeCongeStep.CLOSE){
             return objectMapper.convertValue(demandeConge,DemandeCongeResponseDto.class);
         }
         ServiceDemandeCongeWorkflow workflow=new  ServiceDemandeCongeWorkflow(demandeConge);
         // validation N+1
        NiveauValidation niveauValidation = niveauValidationRepository
                .findByOrdreAndTypeDemande(demandeConge.getCurrentStep().getStepCongeIndex()+1, demandeConge.getTypeDemande());
        RoleDto roleDto = roleRestClient.getRoleById(niveauValidation.getRole()).getBody();
        if (!roleDto.getName().equals("ROLE_DIRECTEUR") && !roleDto.getName().equals("ROLE_AGENT_PAIE")) {
            workflow.advanceToNextStep(request, EDemandeCongeStep.VALIDATION_N_1);
        }
        if (demandeConge.getCurrentStep() == EDemandeCongeStep.VALIDATION_N_1 && roleDto.getName().equals("ROLE_DIRECTEUR")) {
            workflow.advanceToNextStep(request, EDemandeCongeStep.VALIDATION_DIRECTEUR);
        }
        if (demandeConge.getCurrentStep() == EDemandeCongeStep.VALIDATION_DIRECTEUR && roleDto.getName().equals("ROLE_AGENT_PAIE")) {
            workflow.advanceToNextStep(request, EDemandeCongeStep.VALIDATION_AGENT_PAIE);
        }
        if (demandeConge.getCurrentStep() == EDemandeCongeStep.VALIDATION_AGENT_PAIE && roleDto.getName().equals("ROLE_AGENT_PAIE")) {
            workflow.advanceToNextStep(request, EDemandeCongeStep.CLOSE);
        }

        AgentResponseDto agentResponseDto = agentRestClient.getChefByAgentId(demandeConge.getAgentValidation()).getBody();
        demandeConge.setAgentValidation(agentResponseDto.getId());
        Validation validation = new Validation();
        validation.setDemande(demandeConge);
        validationRepository.save(validation);
        demandeCongeRepository.save(demandeConge);

        return null;
    }

    @Override
    public Map<String, Object> getAllDemandeCongeByUser(int page, int size,Long agent) {
        Pageable paging = PageRequest.of(page,size);
        Page<DemandeConge> pages =demandeCongeRepository.findAllByActiveTrueAndAgent(paging,agent);
        List<DemandeCongeResponseDto> formatListDto = pages.getContent().stream().map(data->objectMapper.convertValue(data,DemandeCongeResponseDto.class)).toList();
        return pageListMapper.getPageToMapObject(
                formatListDto,
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages()
        );
    }

    @Override
    public Map<String, Object> getAllDemandeConge(int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<DemandeConge> pages =demandeCongeRepository.findAllByActiveTrue(paging);
        List<DemandeCongeResponseDto> formatListDto = pages.getContent().stream().map(data->objectMapper.convertValue(data,DemandeCongeResponseDto.class)).toList();
        return pageListMapper.getPageToMapObject(
                formatListDto,
                pages.getNumber(),
                pages.getTotalElements(),
                pages.getTotalPages()
        );
    }
    @Override
    public DemandeCongeResponseDto getDemandeCongeById(Long id) {
        return objectMapper.convertValue(demandeCongeRepository.findById(id),DemandeCongeResponseDto.class) ;
    }

    @Override
    public DemandeCongeResponseDto createDemande(DemandeCongeRequestCreateDto request) {
        DemandeConge demandeConge=new DemandeConge();
        ServiceDemandeCongeWorkflow workflow=new  ServiceDemandeCongeWorkflow(demandeConge);
        workflow.advanceToNextStep(request, EDemandeCongeStep.INIT);
        workflow.processCurrentStep();
        AgentResponseDto agentResponseDto = agentRestClient.getChefByAgentId(request.getAgent()).getBody();
        demandeConge.setAgentValidation(agentResponseDto.getId());
        demandeCongeRepository.save(demandeConge);
        return objectMapper.convertValue(demandeConge,DemandeCongeResponseDto.class);
    }
}

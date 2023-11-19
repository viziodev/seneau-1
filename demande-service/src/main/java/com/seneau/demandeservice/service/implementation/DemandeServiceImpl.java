package com.seneau.demandeservice.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seneau.demandeservice.data.enumeration.EDemandeCongeStep;
import com.seneau.demandeservice.data.model.subclass.DemandeConge;
import com.seneau.demandeservice.data.repository.DemandeCongeRepository;
import com.seneau.demandeservice.service.DemandeService;
import com.seneau.demandeservice.service.design.conge.ServiceDemandeCongeWorkflow;
import com.seneau.demandeservice.web.dto.request.DemandeCongeRequestCreateDto;
import com.seneau.demandeservice.web.dto.response.DemandeCongeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private final DemandeCongeRepository demandeCongeRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public DemandeCongeResponseDto goToNextStep(Long id, DemandeCongeRequestCreateDto request) {
        DemandeConge demandeConge = demandeCongeRepository.findById(id).orElseThrow();
         if(demandeConge.getCurrentStep()==EDemandeCongeStep.CLOSE){
             return objectMapper.convertValue(demandeConge,DemandeCongeResponseDto.class);
         }
         ServiceDemandeCongeWorkflow workflow=new  ServiceDemandeCongeWorkflow(demandeConge);
         // TODO Step

        return null;
    }

    @Override
    public DemandeCongeResponseDto createDemande(DemandeCongeRequestCreateDto request) {
        DemandeConge demandeConge=new DemandeConge();
        ServiceDemandeCongeWorkflow workflow=new  ServiceDemandeCongeWorkflow(demandeConge);
        workflow.advanceToNextStep(request, EDemandeCongeStep.INIT);
        workflow.processCurrentStep();
        demandeCongeRepository.save(demandeConge);
        return objectMapper.convertValue(demandeConge,DemandeCongeResponseDto.class);
    }
}

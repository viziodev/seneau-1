package com.seneau.demandeservice.service;

import com.seneau.demandeservice.web.dto.request.DemandeCongeRequestCreateDto;
import com.seneau.demandeservice.web.dto.response.DemandeCongeResponseDto;

import java.util.List;
import java.util.Map;

public interface DemandeCongeService {
   DemandeCongeResponseDto createDemande(DemandeCongeRequestCreateDto request);
   DemandeCongeResponseDto goToNextStep(Long id,DemandeCongeRequestCreateDto request);
   Map<String, Object> getAllDemandeCongeByUser(int page, int size,Long agent);
   Map<String, Object> getAllDemandeConge(int page, int size);
   DemandeCongeResponseDto getDemandeCongeById(Long id);

}

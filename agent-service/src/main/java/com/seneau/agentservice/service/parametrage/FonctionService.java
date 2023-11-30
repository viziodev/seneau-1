package com.seneau.agentservice.service.parametrage;

import com.seneau.agentservice.web.dto.request.parametrage.FonctionRequestDto;
import com.seneau.agentservice.web.dto.response.FonctionResponseDto;

public interface FonctionService {
    FonctionResponseDto createFonctionWithPrivilege(FonctionRequestDto fonctionRequestDto);
}

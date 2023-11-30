package com.seneau.agentservice.web.controller.parametrage;

import com.seneau.agentservice.web.dto.request.parametrage.FonctionRequestDto;
import com.seneau.agentservice.web.dto.response.FonctionResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface FonctionController {
    @PostMapping("/privileges")
    FonctionResponseDto createFonctionWithPrivilege(@RequestBody FonctionRequestDto fonctionRequestDto);
}

package com.seneau.agentservice.web.controller.parametrage.implementation;

import com.seneau.agentservice.data.model.Fonction;
import com.seneau.agentservice.data.repository.FonctionRepository;
import com.seneau.agentservice.web.dto.request.parametrage.FonctionRequestDto;
import com.seneau.agentservice.web.dto.response.FonctionResponseDto;
import com.seneau.communs.core.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fonction")
public class FonctionControllerImplement extends GenericController<Fonction, FonctionResponseDto, FonctionRequestDto> {
    public FonctionControllerImplement(FonctionRepository fonctionRepository) {
        super(fonctionRepository, Fonction.class, FonctionResponseDto.class);
    }
}

package com.seneau.demandeservice.web.controller.parametrage;

import com.seneau.communs.core.GenericController;
import com.seneau.demandeservice.data.model.TypeDemande;
import com.seneau.demandeservice.data.repository.TypeDemandeRepository;
import com.seneau.demandeservice.web.dto.request.TypeDemandeRequestCreateDto;
import com.seneau.demandeservice.web.dto.response.TypeDemandeResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/type-demande")
public class TypeDemandeController  extends GenericController<TypeDemande,TypeDemandeResponseDto,TypeDemandeRequestCreateDto> {
    public TypeDemandeController(TypeDemandeRepository repository) {
        super(repository, TypeDemande.class, TypeDemandeResponseDto.class);
    }
}

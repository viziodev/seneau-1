package com.seneau.demandeservice.web.dto.request;

import com.seneau.demandeservice.data.enumeration.EDemandeCongeStep;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DemandeCongeRequestCreateDto extends DemandeRequestDto {
    private Date dateDemandeDebutConge;
    private int  nbreJourDemande;
}

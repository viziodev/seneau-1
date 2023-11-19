package com.seneau.communs.activity.agent.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {
    private Integer matricule;
    private String fullName;
    private String email;
    private Double taux;
    private String sexe;
    private Date dateNaissance;
    private Long id;
    private boolean active = true;
    private AgentDto chef;
    private StatutDto statut;
    private FonctionDto fonction;
    private SecteurDto secteur;
    private DirectionDto direction;
    private EtablissementDto etablissement;
    private EquipeDto equipe;
    private ServiceDto service;
    private List<ContratDto> contrats;
    private List<ApplicationDto> applications;
}

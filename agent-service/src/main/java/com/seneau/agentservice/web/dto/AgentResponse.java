package com.seneau.agentservice.web.dto;

import com.seneau.communs.data.dto.role.PrivilegeResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    private RoleAgentDto role;
    private List<ContratDto> contrats = new ArrayList<>();
    private List<ApplicationAccessDto> applicationAccessAgents = new ArrayList<>();
    private List<PrivilegeResponseDto> privileges = new ArrayList<>();
}

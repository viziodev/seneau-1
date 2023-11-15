package com.seneau.agentservice.web.dto;

import com.seneau.agentservice.data.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private List<Application> applications;
}

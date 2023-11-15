package com.seneau.agentservice.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Etablissement extends AbstractType{
    @OneToMany(mappedBy = "etablissement")
    private List<Secteur> secteurs;
}

package com.seneau.agentservice.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Equipe extends AbstractType{
    @OneToMany(mappedBy = "equipe")
    private List<Agent> agents;
    @OneToOne
    private Agent chef;
    @ManyToOne
    @JoinColumn(name = "secteur", referencedColumnName = "id")
    private Secteur secteur;
}

package com.seneau.demandeservice.data.model;

import com.seneau.demandeservice.data.enumeration.StatutValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Validation extends AbstractEntity {
    private Long agent;
    private StatutValidation statutValidation;
    @ManyToOne
    @JoinColumn(name = "demande", referencedColumnName = "id")
    private Demande demande;
    @ManyToOne
    @JoinColumn(name = "niveauValidation", referencedColumnName = "id")
    private NiveauValidation niveauValidation;
}

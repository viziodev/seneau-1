package com.seneau.agentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ApplicationAccessFonctionPrivilege extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "applicationAccessFonction", referencedColumnName = "id")
    private ApplicationAccessFonction applicationAccessFonction;
    @ManyToOne
    @JoinColumn(name = "privilege", referencedColumnName = "id")
    private Privilege privilege;
}

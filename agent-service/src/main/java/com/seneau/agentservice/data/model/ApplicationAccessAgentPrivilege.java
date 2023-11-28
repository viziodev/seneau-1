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
public class ApplicationAccessAgentPrivilege extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "applicationAccessAgent", referencedColumnName = "id")
    private ApplicationAccessAgent applicationAccessAgent;
    @ManyToOne
    @JoinColumn(name = "privilege", referencedColumnName = "id")
    private Privilege privilege;
}

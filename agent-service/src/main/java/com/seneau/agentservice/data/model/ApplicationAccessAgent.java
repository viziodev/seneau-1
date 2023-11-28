package com.seneau.agentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ApplicationAccessAgent extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "application", referencedColumnName = "id")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "agent", referencedColumnName = "id")
    private Agent agent;
    @OneToMany(mappedBy = "applicationAccessAgent")
    private List<ApplicationAccessAgentPrivilege> applicationAccessAgentPrivileges;
}

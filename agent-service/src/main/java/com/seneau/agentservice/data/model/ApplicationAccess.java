package com.seneau.agentservice.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ApplicationAccess extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "application", referencedColumnName = "id")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "agent", referencedColumnName = "id")
    private Agent agent;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
}

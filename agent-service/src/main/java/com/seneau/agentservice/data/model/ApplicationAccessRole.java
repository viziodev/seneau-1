package com.seneau.agentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ApplicationAccessRole extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "application", referencedColumnName = "id")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
    @OneToMany
    private List<Privilege> privileges;
}

package com.seneau.agentservice.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role extends AbstractType{
    @JoinColumn(unique = false)
    @OneToOne
    Role rolesHierarchique;
    @JoinColumn(unique = false)
    @OneToOne
    Role rolesFonctionnel;
    @OneToMany(mappedBy = "role")
    private List<ApplicationAccessRole> applicationAccessRoles = new ArrayList<>();
}

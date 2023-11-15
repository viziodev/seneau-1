package com.seneau.agentservice.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role extends AbstractType{
    @OneToMany(mappedBy = "role")
    List<ApplicationAccess> applicationAccesses;
    @OneToMany
    List<Role> rolesHierarchique;
}

package com.seneau.agentservice.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Fonction extends AbstractType  {
    @OneToMany(mappedBy = "fonction")
    private List<ApplicationAccessFonction> applicationAccessFonctions = new ArrayList<>();
}

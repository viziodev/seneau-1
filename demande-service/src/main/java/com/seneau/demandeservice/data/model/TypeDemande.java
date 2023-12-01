package com.seneau.demandeservice.data.model;

import com.seneau.communs.core.GenericEntity;
import com.seneau.demandeservice.data.enumeration.ETypeValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TypeDemande extends AbstractType implements GenericEntity<TypeDemande> {
    private Long roleFirstValidation;
    private Long roleLastValidation;
    private ETypeValidation ETypeValidation;
    @OneToMany(mappedBy = "typeDemande")
    private List<NiveauValidation> niveauValidations;

    @Override
    public void update(TypeDemande source) {
        this.setCode(source.getCode());
        this.setName(source.getName());
        this.setActive(source.isActive());
    }
    @Override
    public TypeDemande createNewInstance() {
        TypeDemande fonction = new TypeDemande();
        fonction.update(this);
        return fonction;
    }
}

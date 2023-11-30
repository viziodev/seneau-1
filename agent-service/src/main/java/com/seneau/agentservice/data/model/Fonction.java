package com.seneau.agentservice.data.model;

import com.seneau.communs.core.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE fonction SET active = 0 WHERE id=?")
public class Fonction extends AbstractType implements GenericEntity<Fonction> {
    @OneToMany(mappedBy = "fonction")
    private List<ApplicationAccessFonction> applicationAccessFonctions = new ArrayList<>();

    @Override
    public void update(Fonction source) {
        this.setCode(source.getCode());
        this.setName(source.getName());
        this.setApplicationAccessFonctions(source.getApplicationAccessFonctions());
        this.setActive(source.isActive());
    }

    @Override
    public Fonction createNewInstance() {
        Fonction fonction = new Fonction();
        fonction.update(this);
        return fonction;
    }
}

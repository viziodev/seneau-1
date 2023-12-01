package com.seneau.demandeservice.data.repository;

import com.seneau.demandeservice.data.model.subclass.DemandeConge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DemandeCongeRepository extends JpaRepository<DemandeConge,Long> {
    Page<DemandeConge> findAllByActiveTrue(Pageable pageable);
    Page<DemandeConge> findAllByActiveTrueAndAgent(Pageable pageable,Long angent);

}

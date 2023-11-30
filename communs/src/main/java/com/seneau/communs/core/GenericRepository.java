package com.seneau.communs.core;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>> extends JpaRepository<T, Long> {
    List<T> findAllByActiveTrue();
    Page<T> findAllByActiveTrue(Pageable pageable);

    /*@Modifying
    @Query("Update  #{#entityName} e  set e.active=false Where e.id =:id")
    void updateEntity(@Param("id") Long id);*/
}

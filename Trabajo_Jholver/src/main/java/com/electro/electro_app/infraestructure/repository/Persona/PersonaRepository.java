package com.electro.electro_app.infraestructure.repository.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electro.electro_app.domain.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Custom query methods can be defined here if needed
}

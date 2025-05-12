package com.electro.electro_app.application.service;

import java.util.List;
import java.util.Optional;

import com.electro.electro_app.domain.entities.Persona;

public interface IPersonaService {
    List<Persona> findAll();

    Optional<Persona> findById(Long id);

    Persona save(Persona persona);

    Optional<Persona> update(Long id, Persona persona);

    Optional<Persona> delete(Long id);
}

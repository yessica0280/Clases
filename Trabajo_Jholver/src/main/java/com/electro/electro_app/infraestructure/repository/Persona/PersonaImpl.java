package com.electro.electro_app.infraestructure.repository.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electro.electro_app.application.service.IPersonaService;
import com.electro.electro_app.domain.entities.Persona;

@Service
public class PersonaImpl implements IPersonaService {

    @Autowired
    private PersonaRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Persona> findAll() {
        return(List<Persona>) repository.findAll();}

    @Override
    public Optional<Persona> findById(Long id) {
        return repository.findById(id);    
    }

    @Override
    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    @Override
    public Optional<Persona> update(Long id, Persona persona) {
        Optional<Persona> personaOld = repository.findById(id);
        if (personaOld.isPresent()) {
            Persona personaDb = personaOld.orElseThrow();
            personaDb.setName(persona.getName());
            personaDb.setLastName(persona.getLastName());
            personaDb.setEmail(persona.getEmail());
            personaDb.setPhone(persona.getPhone());
            personaDb.setAddress(persona.getAddress());
            return Optional.of(repository.save(persona));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Persona> delete(Long id) {
        Optional<Persona> personaOptional = repository.findById(id);
        personaOptional.ifPresent(personaDb -> {
            repository.delete(personaDb);
        });
        return Optional.empty();
    }
}

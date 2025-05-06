package com.example.demojpa.infraestructure.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demojpa.application.service.PersonService;
import com.example.demojpa.domain.Person;
import com.example.demojpa.domain.Rol;
import com.example.demojpa.domain.passport;
import com.example.demojpa.domain.dto.PersonRequest;
import com.example.demojpa.domain.dto.PersonResponse;
import com.example.demojpa.infraestructure.error.RoldDuplicationException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final RolRepository roleRepository;
    private final DocumentRepository documentRepository;

    public PersonServiceImpl(PersonRepository personRepository, RolRepository roleRepository, DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
        this.roleRepository = roleRepository;
        this.personRepository=personRepository;
    }

    @Override
    public List<PersonResponse> findAllUsersByFilter(String filter,String value){
        if(filter.toLowerCase().equals("name") && !value.isEmpty()){
            return personRepository.findAll().stream().map((person) -> {
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastname());
                response.setSkill(person.getLanguage());
                response.setPassport(person.getPassport() != null);
                return response;
            }).toList();
        }
        else if(filter.toLowerCase().equals("lenguage") && !value.isEmpty()){
            return personRepository.findAll().stream().map((person) -> {
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastname());
                response.setSkill(person.getLanguage());
                response.setPassport(person.getPassport() != null);
                return response;
            }).toList();
        }
        return personRepository.findAll().stream().map((person) -> {
            PersonResponse response = new PersonResponse();
            response.setName(person.getName());
            response.setSurname(person.getLastname());
            response.setSkill(person.getLanguage());
            response.setPassport(person.getPassport() != null);
            return response;
        }).toList();
        
    }

    
    @Override
    public boolean deletePerson(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            personRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateLenguageName(String id, String newLenguagename) {
       
        Optional<Person> personOpti = personRepository.findById(Long.parseLong(id));
        if (personOpti.isPresent()) {
            Person person = personOpti.get();
            person.setLanguage(newLenguagename);
            personRepository.save(person);
            return true;
        }
        return false; 
        
    }

    @Override
    public PersonResponse patchPerson(Long id, PersonRequest personDTO) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario solicitado"));
        if(personDTO.getName() != null){
            person.setName(personDTO.getName());
        }

        if(personDTO.getSurname() != null){
            person.setLastname(personDTO.getSurname());
        }

        if(personDTO.getSkill() != null){
            person.setLanguage(personDTO.getSkill());
        }

        personRepository.save(person);
        PersonResponse response = new PersonResponse();
        response.setName(person.getName());
        response.setSurname(person.getLastname());
        response.setSkill(person.getLanguage());
        response.setPassport(person.getPassport() != null);
        return response;
    }

    @Override
    public PersonResponse createNewUser(PersonRequest personDTO) {
        Optional<Person> person = personRepository
        .findPersonByPassportNumber(personDTO.getPassport());

        // Validamos que el usuario no este registrado
        if(person.isPresent()){
            throw new RoldDuplicationException(
                "El usuario ya se encuentra registrado", HttpStatus.CONFLICT);
        }

        // Buscamos el rol para el usuario
        Rol userRol = roleRepository.findById(1L)
        .orElseThrow(() -> new EntityNotFoundException(
            "No se encuentra el rol por defecto"));

        // Creamos el usuario temporal
        Person newPerson = new Person();
        newPerson.setName(personDTO.getName());
        newPerson.setLastname(personDTO.getSurname());
        newPerson.setLanguage(personDTO.getSkill());
        newPerson.setRole(userRol);

        // Guardamos nuestro nuevo registro
        Person save = personRepository.save(newPerson);

        // Creamos el passport temporal
        passport Passport = new passport();
        Passport.setPerson(save);
        Passport.setNumber(personDTO.getPassport());

        // Guardar el passport o el documento
        documentRepository.save(Passport);

        // "Mapeo" de Person a PersonResponse
        save.setPassport(Passport);
        PersonResponse response = new PersonResponse();
        response.setName(save.getName());
        response.setSurname(save.getLastname());
        response.setSkill(save.getLanguage());
        response.setPassport(save.getPassport() != null);
        return response;
    }
    
}


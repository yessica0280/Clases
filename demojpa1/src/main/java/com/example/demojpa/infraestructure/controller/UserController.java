package com.example.demojpa.infraestructure.controller;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demojpa.application.service.PersonService;
import com.example.demojpa.domain.dto.PersonRequest;
import com.example.demojpa.domain.dto.PersonResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final PersonService personService ;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/user")
    public List<PersonResponse>findAll(
        @RequestParam(name="filter",defaultValue ="") String filter,
        @RequestParam(name="value",defaultValue ="") String value
        
    ){
        List<PersonResponse> results=personService.findAllUsersByFilter(filter, value);
    
        return results;
    }

    @PostMapping("/user")
    public ResponseEntity<PersonResponse> createNewUser(@Valid 
    @RequestBody PersonRequest personDTO){
        return new ResponseEntity<PersonResponse>(
            personService.createNewUser(personDTO),
            HttpStatusCode.valueOf(201)
        );
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<PersonResponse> parcialUpdatePerson(@PathVariable Long id, 
    @RequestBody PersonRequest personDTO){
        return ResponseEntity.ok().body(personService.patchPerson(id, personDTO));
    }
}

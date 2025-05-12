package com.electro.electro_app.infraestructure.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electro.electro_app.application.service.ICityService;
import com.electro.electro_app.application.service.IPersonaService;
import com.electro.electro_app.application.service.ITipoDocumentoService;
import com.electro.electro_app.domain.DTOs.PersonaRequestDTO;
import com.electro.electro_app.domain.entities.City;
import com.electro.electro_app.domain.entities.Persona;
import com.electro.electro_app.domain.entities.TipoDocumento;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
    @Autowired
    private IPersonaService personaService;

    @Autowired
    private ICityService ciudadService;

    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @GetMapping
    public List<Persona> list() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Persona> personaOptional = personaService.findById(id);
        if (personaOptional.isPresent()) {
            return ResponseEntity.ok(personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonaRequestDTO personaRequestDTO) {
        City ciudad = ciudadService.findById(personaRequestDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        TipoDocumento tipoDocumento = tipoDocumentoService.findById(personaRequestDTO.getTipoDocumentoId())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
        Persona persona = new Persona();
        persona.setName(personaRequestDTO.getName());
        persona.setLastName(personaRequestDTO.getLastName());
        persona.setEmail(personaRequestDTO.getEmail());
        persona.setPhone(personaRequestDTO.getPhone());
        persona.setAddress(personaRequestDTO.getAddress());
        
        persona.setTipoDocumento((tipoDocumento));
        persona.setCity(ciudad);

        Persona savedpersona = personaService.save(persona);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedpersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona persona) {
        Optional<Persona> personaOptional = personaService.findById(id);
        if (personaOptional.isPresent()) {
            Persona updatedPersona = personaOptional.orElseThrow();
            updatedPersona.setName(persona.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(updatedPersona));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Persona> personaOptional = personaService.delete(id);
        if (personaOptional.isPresent()) {
            return ResponseEntity.ok(personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}

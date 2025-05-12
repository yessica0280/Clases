package com.electro.electro_app.infraestructure.Controllers;

import java.util.List;

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

import com.electro.electro_app.application.service.ICountryService;
import com.electro.electro_app.domain.entities.Country;
import com.electro.electro_app.infraestructure.models.exception.EntityNotFoundException;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping
    public List<Country> list() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Country country = countryService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: el país no existe"));
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Country country) {
        Country existingCountry = countryService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: el país no existe"));
        existingCountry.setName(country.getName());
        return ResponseEntity.ok(countryService.save(existingCountry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Country country = countryService.findById(id).orElseThrow(() -> new EntityNotFoundException("Error: el país no existe"));

        countryService.delete(id);
        return ResponseEntity.ok(country);
    }
}
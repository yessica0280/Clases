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
import com.electro.electro_app.application.service.IRegionService;
import com.electro.electro_app.domain.DTOs.CityRequestDTO;
import com.electro.electro_app.domain.entities.City;
import com.electro.electro_app.domain.entities.Region;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private IRegionService regionService;

    @GetMapping
    public List<City> list() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.ok(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CityRequestDTO cityRequestDTO) {
        Region region = regionService.findById(cityRequestDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region no encontrado"));

        City city = new City();
        city.setName(cityRequestDTO.getName());
        city.setRegion(region);

        City savedcity = cityService.save(city);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedcity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()) {
            City updatedCity = cityOptional.orElseThrow();
            updatedCity.setName(city.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(updatedCity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.delete(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.ok(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}

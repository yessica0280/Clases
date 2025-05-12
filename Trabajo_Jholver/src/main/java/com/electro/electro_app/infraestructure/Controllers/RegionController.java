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

import com.electro.electro_app.application.service.ICountryService;
import com.electro.electro_app.application.service.IRegionService;
import com.electro.electro_app.domain.DTOs.RegionRequestDTO;
import com.electro.electro_app.domain.entities.Country;
import com.electro.electro_app.domain.entities.Region;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public List<Region> list() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            return ResponseEntity.ok(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegionRequestDTO regionDTO) {
        Country country = countryService.findById(regionDTO.getCountryId())
                .orElseThrow(() -> new RuntimeException("País no encontrado"));

        Region region = new Region();
        region.setName(regionDTO.getName());
        region.setCountry(country);

        Region savedRegion = regionService.save(region);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Region region) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            Region updatedRegion = regionOptional.orElseThrow();
            updatedRegion.setName(region.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(updatedRegion));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.delete(id);
        if (regionOptional.isPresent()) {
            return ResponseEntity.ok(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}

package com.electro.electro_app.infraestructure.repository.Country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electro.electro_app.application.service.ICountryService;
import com.electro.electro_app.domain.entities.Country;

@Service
public class CountryImpl implements ICountryService {
    
    @Autowired
    private CountryRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Country> findAll() {
        return(List<Country>) repository.findAll();}

    @Override
    public Optional<Country> findById(Long id) {
        return repository.findById(id);    
    }

    @Override
    public Country save(Country country) {
        return repository.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        Optional<Country> countryOld = repository.findById(id);
        if (countryOld.isPresent()) {
            Country countryDb = countryOld.orElseThrow();
            countryDb.setName(country.getName());
            return Optional.of(repository.save(country));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> delete(Long id) {
        Optional<Country> countryOptional = repository.findById(id);
        countryOptional.ifPresent(countryDb -> {
            repository.delete(countryDb);
        });
        return Optional.empty();
    }

    @Override
    public boolean ExistsByCountryName(String countryname) {
        return repository.ExistsByCountryName(countryname);
    }
}
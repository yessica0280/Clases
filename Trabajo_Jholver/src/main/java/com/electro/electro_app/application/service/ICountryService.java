package com.electro.electro_app.application.service;

import java.util.List;
import java.util.Optional;

import com.electro.electro_app.domain.entities.Country;

public interface ICountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Country save(Country country);

    Optional<Country> update(Long id, Country country);

    Optional<Country> delete(Long id);

    boolean ExistsByCountryName(String id);
}

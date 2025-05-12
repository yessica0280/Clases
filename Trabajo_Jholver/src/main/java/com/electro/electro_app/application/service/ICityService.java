package com.electro.electro_app.application.service;

import java.util.List;
import java.util.Optional;

import com.electro.electro_app.domain.entities.City;

public interface ICityService {
    List<City> findAll();

    Optional<City> findById(Long id);

    City save(City city);

    Optional<City> update(Long id, City city);

    Optional<City> delete(Long id);
}

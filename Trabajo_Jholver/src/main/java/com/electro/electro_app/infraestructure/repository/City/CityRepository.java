package com.electro.electro_app.infraestructure.repository.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electro.electro_app.domain.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    // Custom query methods can be defined here if needed

}

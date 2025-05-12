package com.electro.electro_app.infraestructure.repository.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electro.electro_app.domain.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean ExistsByCountryName(String username);
}
package com.electro.electro_app.infraestructure.repository.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electro.electro_app.domain.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // Custom query methods can be defined here if needed
}

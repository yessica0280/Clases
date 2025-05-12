package com.electro.electro_app.infraestructure.repository.Region;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electro.electro_app.application.service.IRegionService;
import com.electro.electro_app.domain.entities.Region;

@Service
public class RegionImpl implements IRegionService {

    @Autowired
    private RegionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Region> findAll() {
        return (List<Region>) repository.findAll();
    }

    @Override
    public Optional<Region> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Region save(Region region) {
        return repository.save(region);
    }

    @Override
    public Optional<Region> update(Long id, Region region) {
        Optional<Region> regionOld = repository.findById(id);
        if (regionOld.isPresent()) {
            Region regionDb = regionOld.orElseThrow();
            regionDb.setName(region.getName());
            return Optional.of(repository.save(region));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Region> delete(Long id) {
        Optional<Region> regionOptional = repository.findById(id);
        regionOptional.ifPresent(regionDb -> {
            repository.delete(regionDb);
        });
        return Optional.empty();
    }
}

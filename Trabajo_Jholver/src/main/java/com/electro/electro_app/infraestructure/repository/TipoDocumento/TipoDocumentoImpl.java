package com.electro.electro_app.infraestructure.repository.TipoDocumento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electro.electro_app.application.service.ITipoDocumentoService;
import com.electro.electro_app.domain.entities.TipoDocumento;

@Service
public class TipoDocumentoImpl implements ITipoDocumentoService {
    
    @Autowired
    private TipoDocumentoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoDocumento> findAll() {
        return(List<TipoDocumento>) repository.findAll();}

    @Override
    public Optional<TipoDocumento> findById(Long id) {
        return repository.findById(id);    
    }

    @Override
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return repository.save(tipoDocumento);
    }

    @Override
    public Optional<TipoDocumento> update(Long id, TipoDocumento tipoDocumento) {
        Optional<TipoDocumento> tipoDocumentoOld = repository.findById(id);
        if (tipoDocumentoOld.isPresent()) {
            TipoDocumento tipoDocumentoDb = tipoDocumentoOld.orElseThrow();
            tipoDocumentoDb.setName(tipoDocumento.getName());
            return Optional.of(repository.save(tipoDocumento));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TipoDocumento> delete(Long id) {
        Optional<TipoDocumento> tipoDocumentoOptional = repository.findById(id);
        tipoDocumentoOptional.ifPresent(tipoDocumentoDb -> {
            repository.delete(tipoDocumentoDb);
        });
        return Optional.empty();
    }
}

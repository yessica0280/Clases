package com.electro.electro_app.application.service;

import java.util.List;
import java.util.Optional;

import com.electro.electro_app.domain.entities.TipoDocumento;;

public interface ITipoDocumentoService{
    List<TipoDocumento> findAll();

    Optional<TipoDocumento> findById(Long id);

    TipoDocumento save(TipoDocumento TipoDocumento);

    Optional<TipoDocumento> update(Long id, TipoDocumento TipoDocumento);

    Optional<TipoDocumento> delete(Long id);
}

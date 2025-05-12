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

import com.electro.electro_app.application.service.ITipoDocumentoService;
import com.electro.electro_app.domain.entities.TipoDocumento;

@RestController
@RequestMapping("/api/tipo_documento")
public class TipoDocumentoController {
    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @GetMapping
    public List<TipoDocumento> list() {
        return tipoDocumentoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoService.findById(id);
        if (tipoDocumentoOptional.isPresent()) {
            return ResponseEntity.ok(tipoDocumentoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoService.save(tipoDocumento));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TipoDocumento tipoDocumento) {
        Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoService.findById(id);
        if (tipoDocumentoOptional.isPresent()) {
            TipoDocumento updatedTipoDocumento = tipoDocumentoOptional.orElseThrow();
            updatedTipoDocumento.setName(tipoDocumento.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoService.save(updatedTipoDocumento));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoService.delete(id);
        if (tipoDocumentoOptional.isPresent()) {
            return ResponseEntity.ok(tipoDocumentoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}

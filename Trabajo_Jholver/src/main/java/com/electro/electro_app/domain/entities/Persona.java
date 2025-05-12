package com.electro.electro_app.domain.entities;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @NotNull(message = "El apellido no puede ser nulo")
    private String lastName;

    @Email(message = "El correo no es válido")
    private String email;

    @NotNull(message = "El número de teléfono no puede ser nulo")
    @Column(length = 20, nullable = false)
    private String phone;

    @NotNull(message = "La dirección no puede ser nula")
    private String address;


    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference("persona-city")
    @NotNull(message = "La ciudad no puede ser nula")
    City city;

    @ManyToOne
    @JoinColumn(name = "tipoDocumento_id")
    @JsonBackReference("tipoDocumento-persona")
    TipoDocumento tipoDocumento;

    @OneToOne(mappedBy = "persona",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Cliente clientes;

    // @OneToOne(mappedBy = "persona",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    // @JsonManagedReference
    // private Empleado empleados;

    // @OneToOne(mappedBy = "persona",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    // @JsonManagedReference
    // private Proveedor proveedores;
    @Embedded
    Audit audit = new Audit();

}

package com.electro.electro_app.domain.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequestDTO {
    private Long cityId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Long tipoDocumentoId;
}

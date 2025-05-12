package com.electro.electro_app.domain.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequestDTO {
    private String name;
    private Long regionId;
}

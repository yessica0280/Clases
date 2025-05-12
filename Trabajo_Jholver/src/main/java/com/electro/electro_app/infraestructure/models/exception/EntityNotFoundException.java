package com.electro.electro_app.infraestructure.models.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    } 
}

package com.github.pedrobonelli.ms_alunos.service.exception;

import jakarta.annotation.Resource;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }

}

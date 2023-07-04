package com.maksimliakhouski.phraseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String entityName, Long id) {
        super(entityName + " with id = " + id + " not found");
    }
}

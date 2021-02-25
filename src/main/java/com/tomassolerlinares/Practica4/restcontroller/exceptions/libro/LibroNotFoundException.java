package com.tomassolerlinares.Practica4.restcontroller.exceptions.libro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibroNotFoundException extends RuntimeException {

    public LibroNotFoundException(String mensaje) {
        super(mensaje);
    }
}

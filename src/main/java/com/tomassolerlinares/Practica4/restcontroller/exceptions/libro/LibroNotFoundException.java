package com.tomassolerlinares.Practica4.restcontroller.exceptions.libro;

public class LibroNotFoundException extends RuntimeException {

    public LibroNotFoundException() {
        super("No se ha podido encontrar ningún libro");
    }

    public LibroNotFoundException(Integer id) {
        super("No se pudo encontrar el libro " + id);
    }
}

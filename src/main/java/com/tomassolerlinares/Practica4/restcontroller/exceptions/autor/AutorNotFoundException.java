package com.tomassolerlinares.Practica4.restcontroller.exceptions.autor;

public class AutorNotFoundException extends RuntimeException {

    public AutorNotFoundException(Integer id){super("No se pudo encontrar el autor " + id);}
}

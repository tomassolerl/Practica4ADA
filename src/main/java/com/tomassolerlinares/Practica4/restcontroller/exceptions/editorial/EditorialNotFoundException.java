package com.tomassolerlinares.Practica4.restcontroller.exceptions.editorial;

public class EditorialNotFoundException extends RuntimeException {

    public EditorialNotFoundException(Integer id){super("No se pudo encontrar la editorial " + id);}
}

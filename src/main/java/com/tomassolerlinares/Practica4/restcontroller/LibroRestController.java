package com.tomassolerlinares.Practica4.restcontroller;

import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.libro.LibroNotFoundException;
import com.tomassolerlinares.Practica4.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class LibroRestController {

    private static final int ITEMS_PER_PAGE = 3;
    private final LibroRepository libroRepository;
    private final LibroService libroService;

    @Autowired
    public LibroRestController(LibroRepository libroRepository, LibroService libroService) {
        this.libroRepository = libroRepository;
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    List<Libro> findAll() {
        List<Libro> libros = (List<Libro>) libroRepository.findAll();
        if(libros.isEmpty()){
            throw new LibroNotFoundException("No se ha encontrado ningún libro.");
        }
        return libros;

    }

    @GetMapping("/libros/busqueda/nombre/{name}")
    List<Libro> findByName(@PathVariable String name) {
        List<Libro> libros = libroRepository.findByNameLike("%" + name + "%");
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro con el nombre '" + name + "'.");
        }
        return libros;
    }

    @GetMapping("/libro/{id}")
    Libro one(@PathVariable Integer id) {
        return libroRepository.findById(id).orElseThrow(() -> new LibroNotFoundException("No se ha encontrado ningún libro con el id '" + id + "'."));
    }

    @GetMapping("/libros/autor/{name}")
    List<Libro> findAllByAutor(@PathVariable String name) {
        List<Libro> libros = libroService.findAllByAutor(name);
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro con el nombre del autor: '" + name + "'.");
        }
        return libros;
    }

    @GetMapping("/libros/editorial/{name}")
    List<Libro> findAllByEditorial(@PathVariable String name) {
        List<Libro> libros = libroService.findAllByEditorial(name);
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro con el nombre de la editorial: '" + name + "'.");
        }
        return libros;
    }

    @GetMapping("/libros/editorial/{editorial}/nombre/{name}")
    List<Libro> findByEditorialAndAutor(@PathVariable String editorial, @PathVariable String name){
        List<Libro> libros = libroService.findByEditorialAndNameLike(editorial, name);
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro de la editorial " + editorial + " con el nombre " + name + ".");
        }
        return libros;
    }

    @GetMapping("/libros/page/{page}")
    Page<Libro> getAll(@PathVariable int page) {
        Page<Libro> libros = libroRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro en la página " + page + ".");
        }
        return libros;
    }

    @GetMapping("/libros/busqueda/")
    Page<Libro> getByName(@RequestParam String name, @RequestParam int page) {
        Page<Libro> libros = libroRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (libros.isEmpty()) {
            throw new LibroNotFoundException("No se ha encontrado ningún libro con el nombre '" + name + "' en la página " + page + ".");
        }
        return libros;
    }

    @PostMapping("/libro/nuevo")
    Libro addLibro(@RequestBody Libro libro) {
        Libro nuevo = libroRepository.save(libro);
        if(nuevo == null){
            throw new LibroNotFoundException("No se ha creado ningún libro.");
        }
        return libroRepository.save(libro);
    }

    @PutMapping("/libro/{id}")
    Libro addOrModify(@PathVariable Integer id, @RequestBody Libro libroEditado) {
        libroRepository.findById(id).map(
                libro -> {
                    libro.setAutor(libroEditado.getAutor());
                    libro.setDescription(libroEditado.getDescription());
                    libro.setEditorial(libroEditado.getEditorial());
                    libro.setName(libroEditado.getName());
                    return libroRepository.save(libro);
                }).orElseGet(() -> {
            libroEditado.setId(id);
            return libroRepository.save(libroEditado);
        });
        if(libroEditado == null){
            throw new LibroNotFoundException("No se ha podido realizar ninguna modificación.");
        }
        return libroEditado;
    }

    @DeleteMapping("/libro/{id}")
    public void delete(@PathVariable Integer id) {
        libroRepository.deleteById(id);
        if(libroRepository.existsById(id)){
            throw new LibroNotFoundException("El libro con id "+id+" no se ha eliminado.");
        }
    }
}

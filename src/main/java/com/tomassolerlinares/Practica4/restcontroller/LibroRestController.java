package com.tomassolerlinares.Practica4.restcontroller;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QAutor;
import com.tomassolerlinares.Practica4.domain.querydsl.QEditorial;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.libro.LibroNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class LibroRestController {

    private static final int ITEMS_PER_PAGE = 3;
    private final LibroRepository libroRepository;
    @Autowired
    private EntityManager em;

    @Autowired
    public LibroRestController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/libros")
    List<Libro> findAll() {
        List<Libro> libros =(List<Libro>) libroRepository.findAll();
        return libros;

    }

    @GetMapping("/libros/busqueda/nombre/{name}")
    List<Libro> findByName(@PathVariable String name) {
        return libroRepository.findByNameLike("%" + name + "%");
    }

    @GetMapping("/libro/{id}")
    Libro one(@PathVariable Integer id){
        return libroRepository.findById(id).orElseThrow(() -> new LibroNotFoundException(id));
    }

    @GetMapping("libros/autor/{name}")
    List<Libro> findAllByAutor(@PathVariable String name){
        QAutor qAutor = QAutor.autor;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qAutor)
                .join(qAutor.libros, qLibro)
                .where(qAutor.name.contains(name));
        return query.fetch();
    }

    @GetMapping("libros/editorial/{name}")
    List<Libro> findAllByEditorial(@PathVariable String name){
        QEditorial qEditorial = QEditorial.editorial;
        QLibro qLibro = QLibro.libro;

        JPAQuery<Libro> query = new JPAQuery<Libro>(em)
                .select(qLibro)
                .from(qEditorial)
                .join(qEditorial.libros, qLibro)
                .where(qEditorial.name.contains(name));
        return query.fetch();
    }

    @GetMapping("/libros/page/{page}")
    Page<Libro> getAll(@PathVariable int page) {
        return libroRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @GetMapping("/libros/busqueda/nombre/{name}/pagina/{page}")
    Page<Libro> getByName(@PathVariable String name, @PathVariable int page) {
        return libroRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @PostMapping("/libro/nuevo")
    Libro addLibro(@RequestBody Libro libro) {
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
        return libroEditado;
    }

    @DeleteMapping("/libro/{id}")
    public void delete(@PathVariable Integer id) {
        libroRepository.deleteById(id);
    }
}

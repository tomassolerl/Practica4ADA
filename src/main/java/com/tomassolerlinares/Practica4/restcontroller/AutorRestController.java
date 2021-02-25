package com.tomassolerlinares.Practica4.restcontroller;

import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.autor.AutorNotFoundException;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.editorial.EditorialNotFoundException;
import com.tomassolerlinares.Practica4.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class AutorRestController {

    private static final int ITEMS_PER_PAGE = 3;
    private final AutorRepository autorRepository;
    private final AutorService autorService;

    @Autowired
    private EntityManager em;

    @Autowired
    public AutorRestController(AutorRepository autorRepository, AutorService autorService) {
        this.autorRepository = autorRepository;
        this.autorService = autorService;
    }

    @GetMapping("/autores")
    List<Autor> findAll() {
        List<Autor> editoriales = (List<Autor>) autorRepository.findAll();
        if(editoriales.isEmpty()){
            throw new EditorialNotFoundException("No se ha encontrado ninguna editorial.");
        }
        return editoriales;

    }

    @GetMapping("/autor/busqueda/nombre/{name}")
    List<Autor> findByName(@PathVariable String name) {
        List<Autor> editoriales = autorRepository.findByNameLike("%" + name + "%");
        if (editoriales.isEmpty()) {
            throw new AutorNotFoundException("No se ha encontrado ningún autor con el nombre '" + name + "'.");
        }
        return editoriales;
    }

    @GetMapping("/autor/{id}")
    Autor one(@PathVariable Integer id) {
        return autorRepository.findById(id).orElseThrow(() -> new AutorNotFoundException("No se ha encontrado ningún autor con el id " + id + "."));
    }

    @GetMapping("/autor/nombre/{name}/libros")
    List<Libro> findAllByAutor(@PathVariable String name) {
        List<Libro> editoriales = autorService.findAllByAutor(name);
        if (editoriales.isEmpty()) {
            throw new AutorNotFoundException("No se ha encontrado ningún libro con el nombre del autor: '" + name + "'.");
        }
        return editoriales;
    }

    @GetMapping("/autores/pagina/{page}")
    Page<Autor> getAll(@PathVariable int page) {
        Page<Autor> editoriales = autorRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (editoriales.isEmpty()) {
            throw new AutorNotFoundException("No se ha encontrado ningún autor en la página " + page + ".");
        }
        return editoriales;
    }

    @GetMapping("/autores/busqueda/")
    Page<Autor> getByName(@RequestParam String name, @RequestParam int page) {
        Page<Autor> libros = autorRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (libros.isEmpty()) {
            throw new AutorNotFoundException("No se ha encontrado ningún autor con el nombre '" + name + "' en la página " + page + ".");
        }
        return libros;
    }

    @PostMapping("/autor/nuevo")
    Autor addLibro(@RequestBody Autor autor) {
        Autor nuevo = autorRepository.save(autor);
        if(nuevo == null){
            throw new AutorNotFoundException("No se ha creado ningún autor.");
        }
        return autorRepository.save(autor);
    }

    @PutMapping("/autor/{id}")
    Autor addOrModify(@PathVariable Integer id, @RequestBody Autor autorEditado) {
        autorRepository.findById(id).map(
                editorial -> {
                    editorial.setName(autorEditado.getName());
                    return autorRepository.save(editorial);
                }).orElseGet(() -> {
            autorEditado.setId(id);
            return autorRepository.save(autorEditado);
        });
        if(autorEditado == null){
            throw new AutorNotFoundException("No se ha podido realizar ninguna modificación.");
        }
        return autorEditado;
    }

    @DeleteMapping("/autor/{id}")
    public void delete(@PathVariable Integer id) {
        autorRepository.deleteById(id);
        if(autorRepository.existsById(id)){
            throw new AutorNotFoundException("El autor con id "+id+" no se ha eliminado.");
        }
    }
}



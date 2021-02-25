package com.tomassolerlinares.Practica4.restcontroller;

import com.tomassolerlinares.Practica4.domain.Editorial;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.editorial.EditorialNotFoundException;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.libro.LibroNotFoundException;
import com.tomassolerlinares.Practica4.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@Validated
public class EditorialRestController {

    private static final int ITEMS_PER_PAGE = 3;
    private final EditorialRepository editorialRepository;
    private final EditorialService editorialService;

    @Autowired
    private EntityManager em;

    @Autowired
    public EditorialRestController(EditorialRepository editorialRepository, EditorialService editorialService) {
        this.editorialRepository = editorialRepository;
        this.editorialService = editorialService;
    }

    @GetMapping("/editoriales")
    List<Editorial> findAll() {
        List<Editorial> editoriales = (List<Editorial>) editorialRepository.findAll();
        if(editoriales.isEmpty()){
            throw new EditorialNotFoundException("No se ha encontrado ninguna editorial.");
        }
        return editoriales;

    }

    @GetMapping("/editorial/busqueda/nombre/{name}")
    List<Editorial> findByName(@PathVariable String name) {
        List<Editorial> editoriales = editorialRepository.findByNameLike("%" + name + "%");
        if (editoriales.isEmpty()) {
            throw new EditorialNotFoundException("No se ha encontrado ninguna editorial con el nombre '" + name + "'.");
        }
        return editoriales;
    }

    @GetMapping("/editorial/{id}")
    Editorial one(@PathVariable Integer id) {
        return editorialRepository.findById(id).orElseThrow(() -> new EditorialNotFoundException("No se ha encontrado ninguna editorial con el id " + id + "."));
    }

    @GetMapping("/editorial/nombre/{name}/libros")
    List<Libro> findAllByAutor(@PathVariable String name) {
        List<Libro> editoriales = editorialService.findAllByEditorial(name);
        if (editoriales.isEmpty()) {
            throw new EditorialNotFoundException("No se ha encontrado ningún libro con el nombre de la editorial: '" + name + "'.");
        }
        return editoriales;
    }

    @GetMapping("/editoriales/pagina/{page}")
    Page<Editorial> getAll(@PathVariable int page) {
        Page<Editorial> editoriales = editorialRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (editoriales.isEmpty()) {
            throw new EditorialNotFoundException("No se ha encontrado ninguna editorial en la página " + page + ".");
        }
        return editoriales;
    }

    @GetMapping("/editoriales/busqueda/")
    Page<Editorial> getByName(@RequestParam String name, @RequestParam int page) {
        Page<Editorial> libros = editorialRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
        if (libros.isEmpty()) {
            throw new EditorialNotFoundException("No se ha encontrado ninguna con el nombre '" + name + "' en la página " + page + ".");
        }
        return libros;
    }

    @PostMapping("/editorial/nuevo")
    Editorial addLibro(@RequestBody Editorial editorial) {
        Editorial nuevo = editorialRepository.save(editorial);
        if(nuevo == null){
            throw new EditorialNotFoundException("No se ha creado ninguna editorial.");
        }
        return editorialRepository.save(editorial);
    }

    @PutMapping("/editorial/{id}")
    Editorial addOrModify(@PathVariable Integer id, @RequestBody Editorial editorialEditado) {
        editorialRepository.findById(id).map(
                editorial -> {
                    editorial.setName(editorialEditado.getName());
                    return editorialRepository.save(editorial);
                }).orElseGet(() -> {
            editorialEditado.setId(id);
            return editorialRepository.save(editorialEditado);
        });
        if(editorialEditado == null){
            throw new EditorialNotFoundException("No se ha podido realizar ninguna modificación.");
        }
        return editorialEditado;
    }

    @DeleteMapping("/editorial/{id}")
    public void delete(@PathVariable Integer id) {
        editorialRepository.deleteById(id);
        if(editorialRepository.existsById(id)){
            throw new EditorialNotFoundException("La editorial con id "+id+" no se ha eliminado.");
        }
    }
}



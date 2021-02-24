package com.tomassolerlinares.Practica4.restcontroller;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Editorial;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QEditorial;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.editorial.EditorialNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class EditorialRestController {

    private static final int ITEMS_PER_PAGE = 3;
    private final EditorialRepository editorialRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    public EditorialRestController(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @GetMapping("/editoriales")
    public List<Editorial> findAll() {
        return (List<Editorial>) editorialRepository.findAll();
    }

    @GetMapping("/editorial/busqueda/nombre={name}")
    List<Editorial> findByName(@PathVariable String name) {
        return editorialRepository.findByNameLike("%" + name + "%");
    }

    @GetMapping("/editorial/{id}")
    Editorial one(@PathVariable Integer id){
        return editorialRepository.findById(id).orElseThrow(() -> new EditorialNotFoundException(id));
    }

    @GetMapping("editorial/editorial={name}/libros")
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

    @GetMapping("/editoriales/page={page}")
    Page<Editorial> getAll(@PathVariable int page) {
        return editorialRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @GetMapping("/editoriales/busqueda/")
    Page<Editorial> getByName(@RequestParam String name, @RequestParam int page) {
        return editorialRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @PostMapping("/editorial/nuevo")
    Editorial addEditorial(@RequestBody Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @PutMapping("/editorial/{id}")
    Editorial addOrModify(@PathVariable Integer id, @RequestBody Editorial editorialEditada) {
        editorialRepository.findById(id).map(
                editorial -> {
                    editorial.setName(editorialEditada.getName());
                    editorial.setLibros(editorialEditada.getLibros());
                    return editorialRepository.save(editorial);
                }).orElseGet(() -> {
            editorialEditada.setId(id);
            return editorialRepository.save(editorialEditada);
        });
        return editorialEditada;
    }

    @DeleteMapping("/editorial/{id}")
    public void delete(@PathVariable Integer id) {
        editorialRepository.deleteById(id);
    }
}



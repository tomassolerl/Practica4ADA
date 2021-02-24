package com.tomassolerlinares.Practica4.restcontroller;

import com.querydsl.jpa.impl.JPAQuery;
import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.domain.querydsl.QAutor;
import com.tomassolerlinares.Practica4.domain.querydsl.QLibro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.restcontroller.exceptions.autor.AutorNotFoundException;
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

    @Autowired
    private EntityManager em;

    @Autowired
    public AutorRestController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autores")
    public List<Autor> findAll() {
        return (List<Autor>) autorRepository.findAll();
    }

    @GetMapping("/autores/busqueda/nombre={name}")
    List<Autor> findByName(@PathVariable String name) {
        return autorRepository.findByNameLike("%" + name + "%");
    }

    @GetMapping("/autor/{id}")
    Autor one(@PathVariable Integer id){
        return autorRepository.findById(id).orElseThrow(() -> new AutorNotFoundException(id));
    }

    @GetMapping("autor/autor={name}/libros")
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

    @GetMapping("/autores/page={page}")
    Page<Autor> getAll(@PathVariable int page) {
        return autorRepository.findAll(PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @GetMapping("/autores/busqueda/")
    Page<Autor> getByName(@RequestParam String name, @RequestParam int page) {
        return autorRepository.findByNameLike("%" + name + "%", PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("id").ascending()));
    }

    @PostMapping("/autor/nuevo")
    Autor addAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/autor/{id}")
    Autor addOrModify(@PathVariable Integer id, @RequestBody Autor autorEditado) {
        autorRepository.findById(id).map(
                autor -> {
                    autor.setName(autorEditado.getName());
                    autor.setLibros(autorEditado.getLibros());
                    return autorRepository.save(autor);
               }).orElseGet(() ->{
                   autorEditado.setId(id);
                   return autorRepository.save(autorEditado);
        });
        return autorEditado;
    }

    @DeleteMapping("/autor/{id}")
    public void delete(@PathVariable Integer id) {
        autorRepository.deleteById(id);
    }
}


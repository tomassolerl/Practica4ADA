package com.tomassolerlinares.Practica4.controller;

import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import com.tomassolerlinares.Practica4.restcontroller.AutorRestController;
import com.tomassolerlinares.Practica4.restcontroller.EditorialRestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroController {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;

    public LibroController(LibroRepository libroRepository, AutorRepository autorRestController, EditorialRepository editorialRestController){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRestController;
        this.editorialRepository = editorialRestController;
    }

    @GetMapping("/")
    public String showIndex(Model model){
        return "index";
    }

    @GetMapping("/view/libro/libros")
    public String getLibros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "libro/list";
    }

    @GetMapping("/view/libro/nuevoLibro")
    public String addLibro(Model model){
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("editoriales", editorialRepository.findAll());
        model.addAttribute("libro", new Libro());
        return "libro/add";
    }

    @PostMapping("/view/libro/libroNuevo")
    public String createLibro(Model model, @ModelAttribute Libro libro){
        libroRepository.save(libro);
        return "libro/created";
    }

    @GetMapping("view/libro/borrarLibro")
    public String deleteLibro(Model model){
        model.addAttribute("libros", libroRepository.findAll());
        model.addAttribute("libro", new Libro());
        return "libro/delete";
    }

    @PostMapping("view/libro/libroBorrado")
    public String deleted(Model model, @ModelAttribute Libro libro){
        System.out.println(libro.toString());
        System.out.println(libroRepository.findById(libro.getId()).toString());
        model.addAttribute("libroBorrado", libroRepository.findById(libro.getId()).get());
        libroRepository.deleteById(libro.getId());
        return "libro/deleted";
    }
}

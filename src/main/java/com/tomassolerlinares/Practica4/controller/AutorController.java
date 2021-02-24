package com.tomassolerlinares.Practica4.controller;

import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("/view/autor/autores")
    public String getAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autor/list";
    }

    @GetMapping("/view/autor/nuevoAutor")
    public String addAutor(Model model){
        model.addAttribute("autor", new Autor());
        return "autor/add";
    }

    @PostMapping("/view/autor/autorNuevo")
    public String createLibro(Model model, @ModelAttribute Autor autor){
        autorRepository.save(autor);
        return "autor/created";
    }
}

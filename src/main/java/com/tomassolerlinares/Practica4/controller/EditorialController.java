package com.tomassolerlinares.Practica4.controller;

import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Editorial;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditorialController {

    private final EditorialRepository editorialRepository;

    public EditorialController(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @GetMapping("/view/editorial/editoriales")
    public String getAutores(Model model) {
        model.addAttribute("editoriales", editorialRepository.findAll());
        return "editorial/list";
    }

    @GetMapping("/view/editorial/nuevaEditorial")
    public String addAutor(Model model){
        model.addAttribute("editorial", new Editorial());
        return "editorial/add";
    }

    @PostMapping("/view/editorial/editorialNueva")
    public String createLibro(Model model, @ModelAttribute Editorial editorial){
        editorialRepository.save(editorial);
        return "editorial/created";
    }
}

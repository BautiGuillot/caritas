package com.caritas.caritas.controller;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/crearPublicacion")
public class AdminPublicacionesController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public String mostrarFormularioPublicacion() {
        return "crearPublicacion";
    }

    @PostMapping
    public String guardarPublicacion(@ModelAttribute Publicacion publicacion) {
        publicacionService.create(publicacion);
        return "redirect:/adminHome";
    }



}

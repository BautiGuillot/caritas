package com.caritas.caritas.controller;

import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPublicacionesController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping
    public String create(@ModelAttribute Publicacion publicacion) {
        publicacionService.create(publicacion);
        return "redirect:/adminHome";
    }
}

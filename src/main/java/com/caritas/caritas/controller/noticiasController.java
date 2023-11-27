package com.caritas.caritas.controller;

import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class noticiasController {
    @Autowired
    private PublicacionService publicacionService;


    @GetMapping("/noticias")
    public String noticias() {
        return "/noticias";
    }


    @GetMapping("/noticias")
    public String mostrarListado(Model model) {
        model.addAttribute("publicaciones",publicacionService.getPublicacionesRecientes());
        return "noticias";
    }

}

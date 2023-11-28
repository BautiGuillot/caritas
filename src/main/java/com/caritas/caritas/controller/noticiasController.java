package com.caritas.caritas.controller;

import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/noticias")
@Controller
public class noticiasController {
    @Autowired
    private PublicacionService publicacionService;


    @GetMapping
    public String mostrarListado(Model model) {
        return "noticias";
    }

}

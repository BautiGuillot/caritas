package com.caritas.caritas.controller;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.AdminService;
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

    @Autowired
    private AdminService adminService;


    @GetMapping
    public String mostrarListado(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admin", admins);
        List<Publicacion> publicaciones = publicacionService.getAll();
        model.addAttribute("publicaciones", publicaciones);
        return "noticias";
    }

    @GetMapping("/detallesPublicacion/{id}")
    public String detallesPublicacion(@PathVariable Long id, Model model) {

        Publicacion publicacion = publicacionService.findById(id);
        model.addAttribute("publicacion", publicacion);

        return "detallesPublicacion";
    }

}

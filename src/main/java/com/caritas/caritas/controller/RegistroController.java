package com.caritas.caritas.controller;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newAdmin")
public class RegistroController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String mostrarFormularioRegistro() {
        return "newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:/login"; /*esto es enviado por url*/
    }
}

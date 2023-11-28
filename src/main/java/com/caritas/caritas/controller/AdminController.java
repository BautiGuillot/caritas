package com.caritas.caritas.controller;


import com.caritas.caritas.model.Admin;
import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.AdminService;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PublicacionService publicacionService;


    @GetMapping("/login")
    public String login() {
        return "/login"; // Debe coincidir con la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String procesarFormularioLogin() {
        // Lógica para procesar el formulario de inicio de sesión (puede ser manejada por Spring Security)
        return "redirect:/adminHome"; // Redirige al usuario a la página de inicio después del inicio de sesión exitoso
    }

    @GetMapping("/adminHome")
    public String index(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admin", admins);
        List<Publicacion> publicaciones = publicacionService.getAll();
        model.addAttribute("publicaciones", publicaciones);
        return "/adminHome";
    }

    @GetMapping("/newAdmin")
    public String mostrarFormularioRegistro() {
        return "/newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping("/newAdmin")
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:/login"; /*esto es enviado por url*/
    }


}

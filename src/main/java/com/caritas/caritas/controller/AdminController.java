package com.caritas.caritas.controller;


import com.caritas.caritas.model.Admin;
import com.caritas.caritas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/login")
    public String login() {
        return "admin/login"; // Debe coincidir con la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String procesarFormularioLogin() {
        // Lógica para procesar el formulario de inicio de sesión (puede ser manejada por Spring Security)
        return "redirect:admin/adminHome"; // Redirige al usuario a la página de inicio después del inicio de sesión exitoso
    }

    @GetMapping("/adminHome")
    public String adminHome() {
        return "admin/adminHome"; // Debe coincidir con la vista de inicio de sesión
    }

    @GetMapping("/newAdmin")
    public String mostrarFormularioRegistro() {
        return "admin/newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping("/newAdmin")
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:admin/login"; /*esto es enviado por url*/
    }


}

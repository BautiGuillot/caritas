package com.caritas.caritas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Debe coincidir con la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String procesarFormularioLogin() {
        // Lógica para procesar el formulario de inicio de sesión (puede ser manejada por Spring Security)
        return "redirect:/adminHome"; // Redirige al usuario a la página de inicio después del inicio de sesión exitoso
    }

    @GetMapping("/adminHome")
    public String adminHome() {
        return "adminHome"; // Debe coincidir con la vista de inicio de sesión
    }


}

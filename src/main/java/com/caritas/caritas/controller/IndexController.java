package com.caritas.caritas.controller;

import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.AdminService;
import com.caritas.caritas.service.EmailService;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class IndexController {

        @Autowired
        private PublicacionService publicacionService;

        @Autowired
        private AdminService adminService;

        @Autowired
        private EmailService emailService;

    @GetMapping("/")
    public String mostrarListado(Model model) {
        //cargar las ultimas 3 publicaciones
        List<Publicacion> publicaciones = publicacionService.getUltimas3PublicacionesCorrectas();
        model.addAttribute("publicaciones", publicaciones);
        return "index";
    }

        @GetMapping("/quienes-somos")
        public String quienesSomos() {
            return "quienes-somos";
        }

        @GetMapping("/donar")
        public String donar() {
            return "donar";
        }

        @PostMapping("/")
        public String subirEmail(String email) {
            emailService.upload(email);
            return "redirect:/";
        }



}

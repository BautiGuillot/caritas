package com.caritas.caritas.controller;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.AdminService;
import com.caritas.caritas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

        @Autowired
        private PublicacionService publicacionService;

        @Autowired
        private AdminService adminService;

    @GetMapping("/")
    public String mostrarListado(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admin", admins);

        // Obtener las últimas 3 publicaciones
        List<Publicacion> publicaciones = publicacionService.getAll();
        int totalPublicaciones = publicaciones.size();
        int indiceInicio = totalPublicaciones - 3;
        if (indiceInicio < 0) {
            indiceInicio = 0;
        }
        List<Publicacion> ultimasPublicaciones = publicaciones.subList(indiceInicio, totalPublicaciones);

        model.addAttribute("publicaciones", ultimasPublicaciones);
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





}

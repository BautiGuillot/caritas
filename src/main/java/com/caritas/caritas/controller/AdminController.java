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

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PublicacionService publicacionService;


    //Seccion de login
    @GetMapping("/login")
    public String login() {
        return "/login"; // Debe coincidir con la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String procesarFormularioLogin() {
        // Lógica para procesar el formulario de inicio de sesión (puede ser manejada por Spring Security)
        return "redirect:/adminHome"; // Redirige al usuario a la página de inicio después del inicio de sesión exitoso
    }



    //seccion de adminHome
    @GetMapping("/adminHome")
    public String index(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admin", admins);
        List<Publicacion> publicaciones = publicacionService.getAll();
        model.addAttribute("publicaciones", publicaciones);
        return "/adminHome";
    }



    //seccion de crear admin
    @GetMapping("/newAdmin")
    public String mostrarFormularioRegistro() {
        return "/newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping("/newAdmin")
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:/login"; /*esto es enviado por url*/
    }



    //seccion de crear publicacion
    @GetMapping("/crearPublicacion")
    public String mostrarFormularioPublicacion() {
        return "crearPublicacion";
    }

    @PostMapping("/crearPublicacion")
    public String guardarPublicacion(@ModelAttribute Publicacion publicacion) {
        publicacionService.create(publicacion);
        return "redirect:/adminHome";
    }



    //seccion de borrar y editar publicacion

    @GetMapping("/delete/{id}")
    public String deletePublicacion(@PathVariable Long id) {
        publicacionService.delete(id);
        return "redirect:/adminHome";
    }

    @GetMapping("/editarPublicacion/{id}")
    public String editarPublicacion(@PathVariable Long id, Model model) {
        // Lógica para recuperar la publicación con la ID proporcionada
        // Esto puede incluir una llamada al repositorio para obtener la publicación desde la base de datos
        // Suponiendo que obtienes la publicación y la pasas al modelo
        Publicacion publicacion = publicacionService.findById(id);
        model.addAttribute("publicacion", publicacion);

        return "editarPublicacion"; // Devuelve la página de edición con los datos de la publicación
    }




}

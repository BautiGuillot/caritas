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
    @GetMapping("/adminHome/newAdmin")
    public String mostrarFormularioRegistro() {
        return "/newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping("/adminHome/newAdmin")
    public String create(@ModelAttribute Admin administrator, @RequestParam(value = "isAdmin", required = false, defaultValue = "false") boolean isAdmin) {
        adminService.create(administrator, isAdmin);
        return "redirect:/adminHome"; /*esto es enviado por url*/
    }

    //seccion de editar admins - lista de usuarios
    @GetMapping("/adminHome/editAdmins")
    public String mostrarListaAdmins(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "/editAdmins";
    }



    //seccion de crear publicacion
    @GetMapping("/adminHome/crearPublicacion")
    public String mostrarFormularioPublicacion() {
        return "crearPublicacion";
    }

    @PostMapping("/adminHome/crearPublicacion")
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

    @GetMapping("/adminHome/editarPublicacion/{id}")
    public String editarPublicacion(@PathVariable Long id, Model model) {
        // Lógica para recuperar la publicación con la ID proporcionada
        // Esto puede incluir una llamada al repositorio para obtener la publicación desde la base de datos
        // Suponiendo que obtienes la publicación y la pasas al modelo
        Publicacion publicacion = publicacionService.findById(id);
        model.addAttribute("publicacion", publicacion);

        return "editarPublicacion"; // Devuelve la página de edición con los datos de la publicación
    }

    @PostMapping("/adminHome/editarPublicacionPost/{id}")
    public String updatePublicacion(@ModelAttribute Publicacion publicacion, @PathVariable("id") Long id, Model model) {
        publicacionService.update(publicacion, id);
        return "redirect:/adminHome";
    }


    //borrar y editar admin
    @GetMapping("/adminHome/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/adminHome";
    }

    @GetMapping("/adminHome/editarAdmin/{id}")
    public String editarAdmin(@PathVariable Long id, Model model) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "editarUsuario";
    }

    @PostMapping("/adminHome/editarUsuario/{id}")
    public String updateUsuario(@ModelAttribute Admin admin, @PathVariable("id") Long id, Model model) {
        adminService.update(admin, id);
        return "redirect:/adminHome";
    }


}

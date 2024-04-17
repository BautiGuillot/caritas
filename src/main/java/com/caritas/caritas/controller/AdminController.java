package com.caritas.caritas.controller;


import com.caritas.caritas.model.Admin;
import com.caritas.caritas.model.Email;
import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.AdminService;
import com.caritas.caritas.service.EmailService;
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

    @Autowired
    private EmailService emailService;


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

    @GetMapping("/loginError")
    public String loginErrorGet() {
        return "/loginError";
    }

    @PostMapping("/loginError")
    public String loginErrorPost() {
        return "redirect:/loginError";
    }


    //seccion de adminHome
    @GetMapping("/adminHome")
    public String index(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admin", admins);
        List<Publicacion> publicaciones = publicacionService.getPublicacionesCorrectas();
        model.addAttribute("publicaciones", publicaciones);
        return "/adminHome";
    }

    @GetMapping("/adminHome/manageUsers")
    public String administrarUsuarios(){
        return  "/manageUsers";
    }



    //seccion de crear admin
    @GetMapping("/adminHome/manageUsers/newAdmin")
    public String mostrarFormularioRegistro() {
        return "/newAdmin"; // Debe coincidir con la vista de crearUsuario
    }

    @PostMapping("/adminHome/manageUsers/newAdmin")
    public String create(@ModelAttribute Admin administrator, @RequestParam(value = "isAdmin", required = false, defaultValue = "false") boolean isAdmin) {
        adminService.create(administrator, isAdmin);
        return "redirect:/adminHome"; /*esto es enviado por url*/
    }



    //seccion de editar admins - lista de usuarios
    @GetMapping("/adminHome/manageUsers/editAdmins")
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

    @GetMapping("/delete/{id}")         //la agregamos a la papelera
    public String deletePublicacion(@PathVariable Long id) {
        publicacionService.reciclarPublicacion(id);
        return "redirect:/adminHome";
    }

    @GetMapping("/adminHome/papelera/delete/{id}")        //la borramos definitivamente
    public String deletePublicacionPapelera(@PathVariable Long id) {
        publicacionService.delete(id);
        return "redirect:/adminHome/papelera";
    }

    @GetMapping("/adminHome/papelera")
    public String publicacionesBorradas(Model model){
        List<Publicacion> publicaciones = publicacionService.getPublicacionesBorradas();
        model.addAttribute("publicaciones", publicaciones);
        return "/papelera";
    }

    @GetMapping("/adminHome/papelera/restore/{id}")       //restauramos la publicacion
    public String restorePublicacion(@PathVariable Long id){
        publicacionService.restorePublicacion(id);
        return "redirect:/adminHome/papelera";
    }

    @GetMapping("/adminHome/editarPublicacion/{id}")
    public String editarPublicacion(@PathVariable Long id, Model model) {
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
    @GetMapping("/adminHome/manageUsers/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/adminHome";
    }

    @GetMapping("/adminHome/manageUsers/editarAdmin/{id}")
    public String editarAdmin(@PathVariable Long id, Model model) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "editarUsuario";
    }

    @PostMapping("/adminHome/manageUsers/editarUsuario/{id}")
    public String updateUsuario(@ModelAttribute Admin admin, @PathVariable("id") Long id, Model model,@RequestParam(value = "locked", required = false, defaultValue = "false") boolean locked) {
        adminService.update(admin, id, locked);
        return "redirect:/adminHome";
    }



    //lista de emails registrados
    @GetMapping("/adminHome/manageUsers/listaEmailsRegistrados")
    public String listaEmailsRegistrados(Model model) {
        List<Email> emails = emailService.getAll();
        model.addAttribute("emails", emails);
        return "/listaEmailsRegistrados";
    }

    @GetMapping("/adminHome/manageUsers/deleteEmail/{id}")
    public String deleteEmail(@PathVariable Long id) {
        emailService.delete(id);
        return "redirect:/adminHome/manageUsers/listaEmailsRegistrados";
    }




}

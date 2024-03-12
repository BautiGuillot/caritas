package com.caritas.caritas.controller;


import com.caritas.caritas.model.User;
import com.caritas.caritas.repository.UserRepository;
import com.caritas.caritas.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @Autowired
    private MailSender mailSender;
    private final UserRepository userRepository; // Debes inyectar un repositorio para usuarios

    @Autowired
    public EmailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/subscribe")
    public String showSubscribeForm(Model model) {
        model.addAttribute("user", new User());
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public String subscribe(@ModelAttribute("user") User user) {
        // Guardar el usuario en la base de datos
        userRepository.save(user);

        // Enviar un correo de confirmación
        String mensaje = "Gracias por suscribirte a Cáritas.";
        mailSender.send("Suscripción Exitosa");

        return "redirect:/index"; // Plantilla de confirmación
    }
}




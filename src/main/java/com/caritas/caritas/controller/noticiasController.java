package com.caritas.caritas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class noticiasController {

    @GetMapping("/noticias")
    public String noticias() {
        return "/noticias";
    }

}

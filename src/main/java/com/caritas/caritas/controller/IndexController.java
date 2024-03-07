package com.caritas.caritas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

        @GetMapping("/")
        public String index() {
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

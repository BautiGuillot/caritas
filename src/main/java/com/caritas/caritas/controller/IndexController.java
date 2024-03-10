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

        @GetMapping("/info_carta_1")
        public String infoCarta1() {
        return "info_carta_1";
    }

        @GetMapping("/info_carta_2")
        public String infoCarta2() {
            return "info_carta_2";
        }

        @GetMapping("/info_carta_3")
        public String infoCarta3() {
            return "info_carta_3";
        }

        @GetMapping("/donar")
        public String donar() {
            return "donar";
        }



}

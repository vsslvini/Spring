package com.senac.projetomvc.controller;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MiscController {

    @GetMapping("/ola")
    public String mostraSaudacao() {
        return "saudacao";
    }

    @GetMapping("/diahora")
    public String mostraDiaHora(Model model) {
        LocalDateTime diaHora = LocalDateTime.now();
        model.addAttribute("data", diaHora.toLocalDate().toString());
        model.addAttribute("hora", diaHora.toLocalTime().toString());
        return "data";
    }
    
    @GetMapping("/temperatura")
    public String converteTemperatura(Model model, @RequestParam(defaultValue="0")int valor){
        float farenheit, kelvin;
        kelvin = valor + 273;
        farenheit = 1.8f*valor + 32;
        
        model.addAttribute("celsius", valor );
        model.addAttribute("farenheit", farenheit);
        model.addAttribute("kelvin", kelvin);
        
        return "temperatura";
    }
}

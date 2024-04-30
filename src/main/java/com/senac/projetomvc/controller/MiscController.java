package com.senac.projetomvc.controller;

import com.senac.projetomvc.controller.model.Pessoa;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String converteTemperatura(Model model, @RequestParam(defaultValue = "0") int valor) {
        float farenheit, kelvin;
        kelvin = valor + 273;
        farenheit = 1.8f * valor + 32;

        model.addAttribute("celsius", valor);
        model.addAttribute("farenheit", farenheit);
        model.addAttribute("kelvin", kelvin);

        return "temperatura";
    }

    @GetMapping("/pessoa")
    public String mostraPessoa(Model model, String nome, @RequestParam(defaultValue = "0") int idade) {
        Pessoa p = new Pessoa();
        if (nome != null && !nome.isBlank()) {
            //extrair sobrenome 
            int indiceEspaco = nome.indexOf(" ");
            if (indiceEspaco < 0) //não encontrou caractere de espaço 
            {
                indiceEspaco = nome.length();// assume então a posição do último caractere 
            }
            p.setNome(nome.substring(0, indiceEspaco));
            p.setSobrenome(nome.substring(indiceEspaco, nome.length()));
        }
        p.setIdade(idade);
        model.addAttribute("pessoa", p);
        return "pessoa";
    }

    @GetMapping("/cadastro")
    public String mostraCadastro(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String recebeCadastro(Model model, @ModelAttribute Pessoa pessoa) {
        model.addAttribute("pessoa", pessoa);
        return "pessoa";
    }
}

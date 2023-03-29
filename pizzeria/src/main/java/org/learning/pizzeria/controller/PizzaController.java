package org.learning.pizzeria.controller;

import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){
        List<Pizza> listaPizze = pizzaRepository.findAll();
        model.addAttribute("list", listaPizze);

        return "/pizze/index";
    }
}

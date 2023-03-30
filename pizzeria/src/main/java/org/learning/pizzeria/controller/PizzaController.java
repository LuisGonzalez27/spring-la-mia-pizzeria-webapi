package org.learning.pizzeria.controller;

import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{pizzeId}")
    public String show(@PathVariable("pizzeId") Integer id, Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("pizza", result.get());
            return "/pizze/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

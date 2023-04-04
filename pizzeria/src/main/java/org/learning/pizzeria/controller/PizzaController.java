package org.learning.pizzeria.controller;

import jakarta.validation.Valid;
import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.PizzaRepository;
import org.learning.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword){
        List<Pizza> pizze;
        if (keyword.isEmpty()){
            pizze = pizzaService.getAllPizze();
        } else {
            pizze = pizzaService.getFilteredPizze(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", pizze);

        return "/pizze/index";
    }

    @GetMapping("/{pizzeId}")
    public String show(@PathVariable("pizzeId") Integer id, Model model){
        try{
            Pizza pizza = pizzaService.getById(id);
            model.addAttribute("pizza", pizza);
            return  "/pizze/show";
        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id : " + id + " non trovata");
        }
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "/pizze/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
        //VALIDATION
        if(bindingResult.hasErrors()){
            return "/pizze/create";
        }

        pizzaRepository.save(formPizza);
        return "redirect:/pizze";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "q") String keyword){

        System.out.println(pizzaRepository.findByNome(keyword));

        List<Pizza> filteredPizze = pizzaRepository.findByNomeContainingIgnoreCase(keyword);
        model.addAttribute("list", filteredPizze);
        return "/pizze/index";
    }
}

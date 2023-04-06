package org.learning.pizzeria.controller;

import jakarta.validation.Valid;
import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.Offerta;
import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.OffertaRepository;
import org.learning.pizzeria.service.OffertaService;
import org.learning.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    @Autowired
    private OffertaService offertaService;
    @Autowired
    private PizzaService pizzaService;
    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Optional<Integer> id, Model model){
        Offerta offerte = new Offerta();
        offerte.setStartOfferDate(LocalDate.now());
        offerte.setEndOfferDate(LocalDate.now().plusMonths(1));
        if(id.isPresent()){
            try {
                Pizza pizza = pizzaService.getById(id.get());
                offerte.setPizza(pizza);
            } catch (PizzaNotFoundException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        model.addAttribute("offerta", offerte);
        return "/offerte/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute Offerta formOfferta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/offerte/create";
        }

        Offerta createOfferta = offertaService.create(formOfferta);
        return "redirect:/pizze/" + Integer.toString(createOfferta.getPizza().getId());
    }
}

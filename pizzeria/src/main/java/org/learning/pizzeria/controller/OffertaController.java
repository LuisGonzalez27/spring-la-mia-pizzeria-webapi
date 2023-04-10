package org.learning.pizzeria.controller;

import jakarta.validation.Valid;
import org.learning.pizzeria.exceptions.OffertaNotFoundException;
import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.AlertMessage;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try {
            Offerta offerte = offertaService.getById(id);
            model.addAttribute("offerta", offerte);
            return "/offerte/create";
        } catch (PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute Offerta formOfferta,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/offerte/create";
        }
        try {
            Offerta updatedOfferta = offertaService.update(id, formOfferta);
            return "redirect:/pizze/" + updatedOfferta.getPizza().getId();
        } catch (OffertaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Integer pizzaId = null;
        try {
            pizzaId = offertaService.delete(id);
            redirectAttributes.addFlashAttribute
                    ("message",new AlertMessage (AlertMessage.AlertMessageType.SUCCESS, "Offerta eliminata!"));
        } catch (OffertaNotFoundException e) {
            redirectAttributes.addFlashAttribute
                    ("message",new AlertMessage (AlertMessage.AlertMessageType.ERROR, "Offerta with id" + e.getMessage() + " not found"));
        } catch (Exception e){
            redirectAttributes.addFlashAttribute
                    ("message",new AlertMessage (AlertMessage.AlertMessageType.ERROR, "Unable to delete offerta"));
        }
        if(pizzaId == null){
            return "redirect:/pizze/";
        }
        return "redirect:/pizze/" + Integer.toString(pizzaId);
    }
}

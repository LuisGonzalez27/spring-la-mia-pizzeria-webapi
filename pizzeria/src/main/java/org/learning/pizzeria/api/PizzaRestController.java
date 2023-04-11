package org.learning.pizzeria.api;

import jakarta.validation.Valid;
import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pizze")
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;
    //Lista di tutte le pizze
    @GetMapping
    public List<Pizza> list(@RequestParam(name= "q")Optional<String> search){
        //Filtrare per nome della pizza
        if(search.isPresent()){
            return pizzaService.getFilteredPizze(search.get());
        }
        return pizzaService.getAllPizze();
    }

    //Singola pizza
    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id){
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    //Creare una nuova pizza
    @PostMapping
    public Pizza create(@RequestBody Pizza pizza){
        return pizzaService.createPizza(pizza);
    }
    //Modificare una pizza esistente
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza){
        try {
            return pizzaService.updatePizza(pizza, id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Cancellare una pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        try {
            boolean success = pizzaService.deleteById(id);
            if( !success){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossibile eliminare la pizza");
            }
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

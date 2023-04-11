package org.learning.pizzeria.api;

import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}

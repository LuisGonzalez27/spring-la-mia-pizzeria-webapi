package org.learning.pizzeria.service;

import org.learning.pizzeria.model.Ingrediente;
import org.learning.pizzeria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getAll(){
        return ingredienteRepository.findAll(Sort.by("name"));
    }
}

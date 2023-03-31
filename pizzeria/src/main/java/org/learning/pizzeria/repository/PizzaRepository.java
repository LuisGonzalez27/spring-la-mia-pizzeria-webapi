package org.learning.pizzeria.repository;

import org.learning.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNomeContainingIgnoreCase(String nome);
    public List<Pizza> findByNome(String nome);

}

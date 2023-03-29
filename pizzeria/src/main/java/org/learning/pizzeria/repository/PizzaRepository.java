package org.learning.pizzeria.repository;

import org.learning.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}

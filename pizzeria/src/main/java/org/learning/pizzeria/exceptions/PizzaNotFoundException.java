package org.learning.pizzeria.exceptions;

public class PizzaNotFoundException  extends  RuntimeException{
    public PizzaNotFoundException(String message){
        super(message);
    }
}

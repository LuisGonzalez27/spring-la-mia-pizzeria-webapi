package org.learning.pizzeria.exceptions;

public class OffertaNotFoundException extends  RuntimeException{
    public OffertaNotFoundException(String message){
        super(message);
    }
}

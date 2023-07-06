package com.hdlg9la.todosimple.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;



// Classe de exceção personalizada para objetos não encontrados
// Estende a classe EntityNotFoundException para aproveitar o comportamento padrão
// A anotação @ResponseStatus define o status HTTP a ser retornado quando essa exceção é lançada (HttpStatus.NOT_FOUND - 404 Not Found)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundExceotion extends EntityNotFoundException {

         public ObjectNotFoundExceotion(String message){
                  super(message);
         }
        
         
}

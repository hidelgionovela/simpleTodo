package com.hdlg9la.todosimple.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Classe DataBindingViolationException
// Essa classe estende a classe DataIntegrityViolationException e representa uma exceção personalizada para violações de ligação de dados.
// Ela é anotada com @ResponseStatus para definir o status HTTP 404 (NOT_FOUND) quando essa exceção é lançada.

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolationException extends DataIntegrityViolationException {

    // Construtor da classe DataBindingViolationException
    // Recebe uma mensagem como parâmetro e repassa essa mensagem para o construtor da classe pai (DataIntegrityViolationException)
    public DataBindingViolationException(String message) {
        super(message);
    }
}


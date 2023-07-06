package com.hdlg9la.todosimple.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    // Classe que representa uma resposta de erro

    private final int status; // O status HTTP da resposta de erro
    private final String message; // A mensagem de erro
    private String stackTrace; // A pilha de chamadas do erro
    private List<ValidationError> errors; // Lista de erros de validação

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class ValidationError {
        // Classe interna que representa um erro de validação

        private final String messages; // Mensagens de erro
        private final String field; // Campo associado ao erro
    }

    public void addValidationError(String field, String message) {
        // Método para adicionar um erro de validação à lista de erros

        if (Objects.isNull(errors)) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(new ValidationError(field, message));
    }
}

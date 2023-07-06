package com.hdlg9la.todosimple.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hdlg9la.todosimple.exceptions.ErrorResponse;
import com.hdlg9la.todosimple.services.exceptions.DataBindingViolationException;
import com.hdlg9la.todosimple.services.exceptions.ObjectNotFoundExceotion;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

//  @Slf4j é usado para registrar mensagens de erro no log
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    
    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    // Método para tratar exceções do tipo MethodArgumentNotValidException
    // Retorna uma resposta com status HTTP 422 (Unprocessable Entity) e um objeto ErrorResponse
    // O objeto ErrorResponse contém informações sobre o erro de validação
    // Constrói o objeto ErrorResponse com as informações do erro de validação
    // Retorno uma resposta com status HTTP 422 e o objeto ErrorResponse no corpo
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.");
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    // Método para tratar exceções do tipo Exception
    // Retorna uma resposta com status HTTP 500 (Internal Server Error) e um objeto ErrorResponse
    // O objeto ErrorResponse contém informações sobre o erro desconhecido
    // Registra o erro desconhecido no log
    // Constrói o objeto ErrorResponse com as informações do erro desconhecido
    // Retorna uma resposta com status HTTP 500 e o objeto ErrorResponse no corpo
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception exception,
            WebRequest request) {
        final String errorMessage = "Unknown error occurred";
        log.error(errorMessage, exception);
        return buildErrorResponse(
                exception,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }


    // Os métodos anotados com @ExceptionHandler lidam com exceções específicas e retornam respostas personalizadas com base no tipo de exceção ocorrido
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException dataIntegrityViolationException,
            WebRequest request) {
        String errorMessage = dataIntegrityViolationException.getMostSpecificCause().getMessage();
        log.error("Failed to save entity with integrity problems: " + errorMessage, dataIntegrityViolationException);
        return buildErrorResponse(
                dataIntegrityViolationException,
                errorMessage,
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException,
            WebRequest request) {
        log.error("Failed to validate element", constraintViolationException);
        return buildErrorResponse(
                constraintViolationException,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }

    @ExceptionHandler(ObjectNotFoundExceotion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundExceotion objectNotFoundException,
            WebRequest request) {
        log.error("Failed to find the requested element", objectNotFoundException);
        return buildErrorResponse(
                objectNotFoundException,
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(DataBindingViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataBindingViolationException(
            DataBindingViolationException dataBindingViolationException,
            WebRequest request) {
        log.error("Failed to save entity with associated data", dataBindingViolationException);
        return buildErrorResponse(
                dataBindingViolationException,
                HttpStatus.CONFLICT,
                request);
    }

    // @ExceptionHandler(AuthenticationException.class)
    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // public ResponseEntity<Object> handleAuthenticationException(
    //         AuthenticationException authenticationException,
    //         WebRequest request) {
    //     log.error("Authentication error ", authenticationException);
    //     return buildErrorResponse(
    //             authenticationException,
    //             HttpStatus.UNAUTHORIZED,
    //             request);
    // }

    // @ExceptionHandler(AccessDeniedException.class)
    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // public ResponseEntity<Object> handleAccessDeniedException(
    //         AccessDeniedException accessDeniedException,
    //         WebRequest request) {
    //     log.error("Authorization error ", accessDeniedException);
    //     return buildErrorResponse(
    //             accessDeniedException,
    //             HttpStatus.FORBIDDEN,
    //             request);
    // }

    // @ExceptionHandler(AuthorizationException.class)
    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // public ResponseEntity<Object> handleAuthorizationException(
    //         AuthorizationException authorizationException,
    //         WebRequest request) {
    //     log.error("Authorization error ", authorizationException);
    //     return buildErrorResponse(
    //             authorizationException,
    //             HttpStatus.FORBIDDEN,
    //             request);
    // }


    // Método para construir o objeto ErrorResponse com base no erro, mensagem e status HTTP
    // Constrói o objeto ErrorResponse com as informações fornecidas
    // Retorna uma resposta com o status HTTP e o objeto ErrorResponse no corpo
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    // Método para construir o objeto ErrorResponse com base no erro, mensagem e status HTTP
    //  buildErrorResponse() é usado para construir o objeto ErrorResponse contendo informações sobre o erro
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (this.printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    // @Override
    // public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    //         AuthenticationException exception) throws IOException, ServletException {
    //     Integer status = HttpStatus.UNAUTHORIZED.value();
    //     response.setStatus(status);
    //     response.setContentType("application/json");
    //     ErrorResponse errorResponse = new ErrorResponse(status, "Username or password are invalid");
    //     response.getWriter().append(errorResponse.toJson());
    // }

}
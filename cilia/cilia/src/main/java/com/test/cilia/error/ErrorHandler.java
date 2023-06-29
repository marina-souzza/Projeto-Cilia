package com.test.cilia.error;

import java.util.HashMap;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(com.test.cilia.error.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlerNotFoundException( com.test.cilia.error.NotFoundException exception, HttpServletRequest request){

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());
        return apiError;
    }

    public ApiError handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        
        HashMap<String, String> listErrors= new HashMap<>();

        for( FieldError fieldError: exception.getFieldErrors()){
            listErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Cliente inválido!", request.getRequestURI());
        
        apiError.setValidationErrorsFields(listErrors);

        return apiError;
    }
    
}

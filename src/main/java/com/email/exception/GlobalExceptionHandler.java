package com.email.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorDetails> entityNotFoundExceptionHandlerMethod(EmailException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionMethod(MethodArgumentNotValidException ex){

        ErrorDetails errorDetails = null;
        ObjectError errorMessage = ex.getBindingResult().getFieldError();
        List<String> errorMessageList = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            errorMessageList.add(error.getDefaultMessage());
        }

        if(errorMessageList.size() > 1){
            errorDetails = new ErrorDetails(errorMessageList.toString(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        else if(errorMessageList != null){
            errorDetails = new ErrorDetails(errorMessage.getDefaultMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExtensionNotValidException.class)
    public ResponseEntity<Object> extensionNotValidExceptionMethod(ExtensionNotValidException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }
}

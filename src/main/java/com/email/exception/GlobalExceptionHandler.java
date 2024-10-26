package com.email.exception;

import com.email.builder.ApiResponseBuilder;
import com.email.builder.dto.ApiResponse;
import com.email.builder.dto.FieldError;

import jakarta.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return ApiResponseBuilder.getErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadApiRequestException.class})
    public ResponseEntity<ApiResponse> handleBadApiRequestException(BadApiRequestException ex) {
        return StringUtils.isNotBlank(ex.getCustomStatusCode()) ? ApiResponseBuilder.getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getCustomStatusCode(), (List)null) : ApiResponseBuilder.getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = new ArrayList(5);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.add(new FieldError(fieldName, errorMessage));
        });
        return ApiResponseBuilder.getErrorResponse((Object)null, "Validation Failed", HttpStatus.BAD_REQUEST, fieldErrors);

    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiResponse> handlerConstraintViolationException(ConstraintViolationException ex) {
        List<FieldError> fieldErrors = new ArrayList(5);
        if (ex.getConstraintViolations() != null && !ex.getConstraintViolations().isEmpty()) {
            ex.getConstraintViolations().forEach((violation) -> {
                String fieldName = violation.getPropertyPath().toString();
                String errorMessage = violation.getMessage();
                fieldErrors.add(new FieldError(fieldName, errorMessage));
            });
        }

        return ApiResponseBuilder.getErrorResponse((Object)null, "Validation Failed", HttpStatus.BAD_REQUEST, fieldErrors);
    }
}

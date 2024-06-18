package com.email.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorDetails {

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;
    private HttpStatus status;

    public ErrorDetails(){
        super();
        this.time = LocalDateTime.now();
    }

    public ErrorDetails(String message, HttpStatus status, LocalDateTime time){
        this.message = message;
        this.status = status;
        this.time = time;
    }

}

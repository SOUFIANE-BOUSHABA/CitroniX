package com.example.xcitronix.advice;


import com.example.xcitronix.exciption.SuperficierException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SuperficierException.class)
    public ResponseEntity<String> handleSuperficierException(SuperficierException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

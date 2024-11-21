package com.example.xcitronix.advice;


import com.example.xcitronix.exciption.ArbreException;
import com.example.xcitronix.exciption.ChampException;
import com.example.xcitronix.exciption.SuperficierException;
import com.example.xcitronix.exciption.VenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArbreException.class)
    public ResponseEntity<String> handleArbreException(ArbreException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ChampException.class)
    public ResponseEntity<String> handleChampException(ChampException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SuperficierException.class)
    public ResponseEntity<String> handleSuperficierException(SuperficierException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(VenteException.class)
    public ResponseEntity<String> handleVenteException(VenteException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

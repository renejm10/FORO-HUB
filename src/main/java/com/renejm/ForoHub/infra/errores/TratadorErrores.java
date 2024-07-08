package com.renejm.ForoHub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionIntegridad.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError mensjae) {
            this(mensjae.getField(), mensjae.getDefaultMessage());
        }
    }
}

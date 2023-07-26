package com.crud.diver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DiversLogNotFoundException.class)
    public ResponseEntity<Object> handleDiversLogNotFoundException(DiversLogNotFoundException exception) {
        return new ResponseEntity<>("Diver's Log with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<Object> handleEquipmentNotFoundException(EquipmentNotFoundException exception) {
        return new ResponseEntity<>("Equipment with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DivingBaseNotFoundException.class)
    public ResponseEntity<Object> handleDivingBaseNotFoundException(DivingBaseNotFoundException exception) {
        return new ResponseEntity<>("Diving base with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
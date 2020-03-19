package com.group3.courseenrollment.controller;

import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.exception.HasNoWriteException;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;


/**
 * {@link ErrorAdvice} Controller to handle any 500 errors
 */

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(final Throwable throwable){
        return ResponseEntity.badRequest().body("Unable to process request at this time.Please try again later");
    }

    @ExceptionHandler({NoSuchResourceException.class,NoSuchElementException.class})
    public ResponseEntity<?> notFoundResource(){
        return new ResponseEntity<>("Resource is not found on our server",HttpStatus.NOT_FOUND);
    }


    // Method Security: Checks if it's an instance of Access denied
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> accessDenied(){
        return new ResponseEntity<>("Operation is denied for your role",HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EnrollmentLimitExceededException.class)
    public ResponseEntity<?> handleEnrollmentLimitException(final Throwable t){
        return new ResponseEntity<>(t.getMessage(),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleConstraintVoilationException(final Throwable t){
        return new ResponseEntity<>("Request rejected. Please check and verify your input",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HasNoWriteException.class})
    public ResponseEntity<?> handleHasNoWriteException(final Throwable t){
        return new ResponseEntity<>(t.getMessage(),HttpStatus.FORBIDDEN);
    }
}

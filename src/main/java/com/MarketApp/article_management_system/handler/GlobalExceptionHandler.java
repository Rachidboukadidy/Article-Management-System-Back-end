package com.MarketApp.article_management_system.handler;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;

import static com.MarketApp.article_management_system.handler.BusinessErrorCodes.*;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse>handelException(LockedException exception){
    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                  ExceptionResponse.builder()
                          .businessErrorCode(ACCOUNT_LOCKED.getCode())
                          .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                          .error(exception.getMessage())
                          .build()
            );
    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse>handelException(DisabledException exception){
    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                  ExceptionResponse.builder()
                          .businessErrorCode(ACCOUNT_DISABLED.getCode())
                          .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                          .error(exception.getMessage())
                          .build()
            );
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse>handelException(BadCredentialsException exception){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error(exception.getMessage())
                                .build()
                );
    }
    // when the user send an invalid data

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse>handelException(MessagingException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse>handelException(MethodArgumentNotValidException exception){
        Set<String> errors =new HashSet<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error ->{
                            var errorMessage =error.getDefaultMessage();
                            errors.add(errorMessage);
                        }
                      );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .ValidationErrors(errors)
                                .build()
                );
    }

    // for handling any exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse>handelException(Exception exception){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("internal error")
                                .error(exception.getMessage())
                                .build()
                );
    }
}

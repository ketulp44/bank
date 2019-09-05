/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.Exceptions.AccountNotFoundException;
import com.example.bank.Exceptions.TransactionException;
import com.example.bank.dto.ApiError;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ketul
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(AccountNotFoundException.class)
  public final ResponseEntity<ApiError> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
    ApiError errorDetails = new ApiError(HttpStatus.UNAUTHORIZED,ex.getMessage(),"enter valid AccountNo");
        System.out.println(errorDetails.toString());
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }
  @ExceptionHandler(TransactionException.class)
  public final ResponseEntity<ApiError> handleInvalidTransactionException(TransactionException ex, WebRequest request) {
    ApiError errorDetails = new ApiError(HttpStatus.UNAUTHORIZED,ex.getMessage(),"transaction error");
      System.out.println("401");
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }
}

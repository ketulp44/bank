/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ketul
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TransactionException extends RuntimeException {

    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }
    
}

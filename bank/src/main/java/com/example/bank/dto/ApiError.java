/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.dto;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author ketul
 */
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
 
    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    @Override
    public String toString() {
        return "ApiError{" + "status=" + status + ", message=" + message + ", errors=" + errors + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;
import com.example.bank.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author world
 */
@RestController
@RequestMapping("/bank")
public class AccountController {
    private AccountService accountService;

    public AccountController() {
    }
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/account")
    public void saveAccount(@RequestBody Account account){     
        this.accountService.saveAccount(account);        
    }
    @GetMapping("/banks")
    public List<Bank> getbanks(){        
        return this.accountService.getBankNames();        
    }
    @PostMapping("/maketransaction")
    public void makeTransaction(@RequestBody TransactionDto transactionDto){
        System.out.println("account " + transactionDto);
        this.accountService.saveTransaction();
    }
   
}

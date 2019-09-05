/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransactionStatement;
import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.service.AccountService;
import com.example.bank.service.StorageImageService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author world
 */
@RestController
@RequestMapping("/bank")
public class AccountController {
    private AccountService accountService;
    private StorageImageService storageImageService;
    public AccountController() {
    }
    @Autowired
    public AccountController(AccountService accountService,StorageImageService storageImageService) {
        this.accountService = accountService;
        this.storageImageService =storageImageService;
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
        this.accountService.saveTransaction(transactionDto);
    }
    
    @GetMapping("/transactions/{accountNo}/{pageNo}")
    public Page<TransactionStatement> getTransactions( @PathVariable Long accountNo, @PathVariable int pageNo){                
        return this.accountService.getTransactions(accountNo, pageNo);        
    }
    @PostMapping("/imageupload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            this.storageImageService.store(file, file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL");
        }
    }   
}

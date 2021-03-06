/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.service;

import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransactionStatement;
import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author world
 */
public interface AccountService {
    public void saveAccount(Account account);
    public List<Bank> getBankNames();
    public void saveTransaction(TransactionDto transactionDto);
    public Page<TransactionStatement> getTransactions(Long accountNo, int page);
    
}

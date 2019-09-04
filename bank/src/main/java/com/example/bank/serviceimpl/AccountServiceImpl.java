/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.serviceimpl;

import com.example.bank.dao.AccountDao;
import com.example.bank.dao.BankDao;
import com.example.bank.dao.TransactionDao;
import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;
import com.example.bank.service.AccountService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author world
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private BankDao bankDao;
    private TransactionDao transactionDao;

    public AccountServiceImpl() {
    }

    @Autowired
    public AccountServiceImpl(AccountDao accountDao, BankDao bankDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.bankDao = bankDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public void saveAccount(Account account) {
        if (account.getAccountType().equals("saving")) {
            System.out.println("inside saving");
            account.setBalance(500L);
        } else if (account.getAccountType().equals("current")) {
            account.setBalance(50000L);
        }
        System.out.println("Account " + account);
        this.accountDao.save(account);
    }

    @Override
    public List<Bank> getBankNames() {
        return this.bankDao.findAll();
    }

    public int findNoOfTransaction(Long accountNo) {
        return this.transactionDao.getLastMonthTransactionNo(accountNo);
    }
    public Transaction extractTransaction(TransactionDto transactionDto, Account account){
        Date date= new Date(); 
        long time = date.getTime(); 
        Timestamp ts = new Timestamp(time);
        long balance = 0;
        if(transactionDto.getTransactionType().equals("credit")){
            balance =account.getBalance() + transactionDto.getAmount();
        }else if(transactionDto.getTransactionType().equals("debit")){
            if(account.getBalance()<transactionDto.getAmount()){
                System.out.println("not enough amount ");
                return null;
            }
            else{
                balance =account.getBalance() - transactionDto.getAmount();
            }
        }
        return new Transaction(
                ts,
        transactionDto.getTransactionType(),
        transactionDto.getAccountNo(),
        transactionDto.getAmount(),
        transactionDto.getTransactionVia(),
        balance        
        );
    }

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Account account = null;
        try {
            account = this.accountDao.findById(transactionDto.getAccountNo()).get();
        } catch (NoSuchElementException ex) {
            System.out.println("no such account ");
        }
        if (transactionDto.getAccountType().equals("saving")) {
            int count = findNoOfTransaction(transactionDto.getAccountNo());
            if (count < 5) {
                Transaction transaction = extractTransaction(transactionDto,account);
                this.transactionDao.save(transaction);
                account.setBalance(transaction.getBalance());
                this.accountDao.save(account);                
            } else {
                System.out.println("can not make transaction more than 5 times in a Month");
            }
        }
        else{
            Transaction transaction = extractTransaction(transactionDto,account);
                this.transactionDao.save(transaction);
                account.setBalance(transaction.getBalance());
                this.accountDao.save(account);
        }

    }

}


package com.example.bank.serviceimpl;

import com.example.bank.Exceptions.AccountNotFoundException;
import com.example.bank.Exceptions.TransactionException;
import com.example.bank.dao.AccountDao;
import com.example.bank.dao.BankDao;
import com.example.bank.dao.TransactionDao;
import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransactionStatement;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


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
            account.setBalance(500L);
        } else if (account.getAccountType().equals("current")) {
            account.setBalance(50000L);
        }        
        this.accountDao.save(account);
    }

    @Override
    public List<Bank> getBankNames() {
        return this.bankDao.findAll();
    }

    public Long findNoOfTransaction(Long accountNo) {
        return this.transactionDao.getLastMonthTransactionNo(accountNo);
    }
    public Transaction extractTransaction(TransactionDto transactionDto, Account account){
        Date date= new Date(); 
        long time = date.getTime(); 
        Timestamp ts = new Timestamp(time);
        long balance = 0;
        //check for transaction type 
        if(transactionDto.getTransactionType().equals("credit")){
            balance =account.getBalance() + transactionDto.getAmount();
        }else if(transactionDto.getTransactionType().equals("debit")){
            // checks if there is enough balance in account
            if(account.getBalance()<transactionDto.getAmount()){                
                throw new AccountNotFoundException("Do not have sufficient Amount in your Account");
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
            
            throw new AccountNotFoundException("Account not found");
        }
        // match accounts type of the given account no
        if(!account.getAccountType().equals(transactionDto.getAccountType())){
            throw new AccountNotFoundException("Account type not matching with account");
        }
        if (transactionDto.getAccountType().equals("saving")) {
            Long count = findNoOfTransaction(transactionDto.getAccountNo());
            // to avoid null pointer exception
            if(count == null){
                count = 0L;
            }
            // to restrict max transaction to 5 for saving account
            if (count < 5) {
                Transaction transaction = extractTransaction(transactionDto,account);
                this.transactionDao.save(transaction);
                account.setBalance(transaction.getBalance());
                // for saving panNo
                if (transactionDto.getAmount() > 50000 && transactionDto.getPanNo() != null) {
                    account.setPanNo(transactionDto.getPanNo());
                }
                if (transactionDto.getAmount() > 50000 && transactionDto.getPanNo() != null) {
                    account.setPanImgUrl(transactionDto.getPanImgUrl());
                }
                this.accountDao.save(account);                
            } else {                
                throw new TransactionException("can not make transaction more than 5 times in a Month");
            }
        }
        //for current Account
        else{
            Transaction transaction = extractTransaction(transactionDto,account);
                this.transactionDao.save(transaction);
                account.setBalance(transaction.getBalance());
                this.accountDao.save(account);
        }

    }

    @Override
    public Page<TransactionStatement> getTransactions(Long accountNo, int page) {                
        return this.transactionDao.getTransactions(accountNo,PageRequest.of(page, 10));        
    }

}

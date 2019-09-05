/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ketul
 */
public class TransactionStatement {
    private Date time;
    private String transactionVia;
    private String transactionType;
    private Long amount;
    private Long balance;

    public TransactionStatement() {
    }

    public TransactionStatement(Date time, String transactionVia, String transactionType, Long amount, Long balance) {
        this.time = time;
        this.transactionVia = transactionVia;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTransactionVia() {
        return transactionVia;
    }

    public void setTransactionVia(String transactionVia) {
        this.transactionVia = transactionVia;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TransactionStatement{" + "time=" + time + ", transactionVia=" + transactionVia + ", transactionType=" + transactionType + ", amount=" + amount + ", balance=" + balance + '}';
    }
    
}

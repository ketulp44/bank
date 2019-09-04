/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.model;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ketul
 */
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Long transactionId;
    @Column(name="time")
    private Timestamp time;
    @Column(name="transaction_type")
    private String transactionType;
    @Column(name="account_id")
    private Long accountId;
    @Column(name="amount")
    private Long amount;    
    @Column(name="transaction_via")
    private String transactionVia;
    @Column(name="balance")
    private Long balance;

    public Transaction() {
    
    }

    public Transaction(Timestamp time, String transactionType, Long accountId, Long amount, String transactionVia, Long balance) {
        this.time = time;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionVia = transactionVia;
        this.balance = balance;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionVia() {
        return transactionVia;
    }

    public void setTransactionVia(String transactionVia) {
        this.transactionVia = transactionVia;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", time=" + time + ", transactionType=" + transactionType + ", accountId=" + accountId + ", amount=" + amount + ", transactionVia=" + transactionVia + ", balance=" + balance + '}';
    }
    
    
}

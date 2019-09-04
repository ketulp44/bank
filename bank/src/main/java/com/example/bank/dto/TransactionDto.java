/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.dto;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ketul
 */
public class TransactionDto {
    private String transactionType;
    private Long accountNo;
    private Long amount;
    private String transactionVia;
    private String accountType;
    private String panNo;

    public TransactionDto() {
    }

    public TransactionDto(String transactionType, Long accountNo, Long amount, String transactionVia, String accountType, String panNo) {
        this.transactionType = transactionType;
        this.accountNo = accountNo;
        this.amount = amount;
        this.transactionVia = transactionVia;
        this.accountType = accountType;
        this.panNo = panNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    @Override
    public String toString() {
        return "TransactionDto{" + "transactionType=" + transactionType + ", accountNo=" + accountNo + ", amount=" + amount + ", transactionVia=" + transactionVia + ", accountType=" + accountType + ", panNo=" + panNo + '}';
    }
    
}

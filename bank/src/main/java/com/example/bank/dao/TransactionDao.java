/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.dao;

import com.example.bank.dto.TransactionStatement;
import com.example.bank.model.Transaction;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ketul
 */
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>{
    
    @Query("select count(t.accountId) from Transaction t where t.accountId = ?1 and YEAR(CURRENT_DATE)= YEAR(t.time) and MONTH(CURRENT_DATE)= MONTH(t.time) GROUP BY t.accountId")
    Long getLastMonthTransactionNo(Long AccountNo);
    
    @Query("select new com.example.bank.dto.TransactionStatement( t.time , t.transactionVia , t.transactionType , t.amount , t.balance ) From Transaction t where t.accountId = ?1 order by t.time Desc")
    Page<TransactionStatement> getTransactions(Long accountNo,Pageable pageable);
}

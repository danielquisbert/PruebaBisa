package com.bisa.prueba.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bisa.prueba.persistence.entity.Transaction;
import com.bisa.prueba.persistence.repository.TransactionRepository;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
    
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public Transaction withdraw(Transaction transaction) {
        return null;
    }

    public Transaction getTransactionById(Long id) {
        return null;
    }

    public Transaction deposit(Transaction transaction) {
        return null;
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        return null;
    }

    public void createTransaction(Transaction transaction) {
    }
    
}

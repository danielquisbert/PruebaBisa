package com.bisa.prueba.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bisa.prueba.persistence.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long accountId);
}
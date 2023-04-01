package com.bisa.prueba.services;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bisa.prueba.persistence.entity.Account;
import com.bisa.prueba.persistence.entity.AccountStatus;
import com.bisa.prueba.persistence.entity.Transaction;
import com.bisa.prueba.persistence.entity.TransactionType;
import com.bisa.prueba.persistence.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        

        if (account.getStatus() == AccountStatus.HOLD) {
            new AccountHoldException("Cannot withdraw from account in hold status");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            account.setStatus(AccountStatus.HOLD);
        }

        account.setBalance(account.getBalance().subtract(amount));
        account = accountRepository.save(account);

        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, account);
        transactionService.createTransaction(transaction);

        return account;
    }

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        

        if (!account.getCurrency().equals(amount)) {
            new CurrencyMismatchException("Deposit currency does not match account currency");
        }

        if (account.getStatus() == AccountStatus.HOLD && account.getBalance().add(amount).compareTo(BigDecimal.ZERO) >= 0) {
            account.setStatus(AccountStatus.ACTIVE);
        }

        account.setBalance(account.getBalance().add(amount));
        account = accountRepository.save(account);

        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, account);
        transactionService.createTransaction(transaction);

        return account;
    }

    public BigDecimal getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
       

        return account.getBalance();
    }

    public void updateAccount(Account updatedAccount) {
    }
}

package com.bisa.prueba.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bisa.prueba.persistence.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //Account findByCurrency(String currency);
}

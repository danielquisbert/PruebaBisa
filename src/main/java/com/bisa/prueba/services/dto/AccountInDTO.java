package com.bisa.prueba.services.dto;

import java.math.BigDecimal;

import com.bisa.prueba.persistence.entity.AccountStatus;

import lombok.Data;

@Data
public class AccountInDTO {
    private BigDecimal balance;
    private AccountStatus status;
}

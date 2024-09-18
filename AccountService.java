package com.aman.BankingApplication.service;

import com.aman.BankingApplication.DTO.AccountDto;
import com.aman.BankingApplication.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(long id);
    AccountDto deposit(long id, double amount);
    AccountDto withdraw(long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(long id);
}

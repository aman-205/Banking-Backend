package com.aman.BankingApplication.service.impl;

import com.aman.BankingApplication.DTO.AccountDto;
import com.aman.BankingApplication.entity.Account;
import com.aman.BankingApplication.mapper.AccountMapper;
import com.aman.BankingApplication.repository.AccountRepository;
import com.aman.BankingApplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {

        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dosn't exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {

        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dosn't exist"));
        double totalBalance=account.getBalance()+amount;
        account.setBalance(totalBalance);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dosn't exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        double totalBalance=account.getBalance()-amount;
        account.setBalance(totalBalance);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dosn't exist"));
        accountRepository.delete(account);
    }

}

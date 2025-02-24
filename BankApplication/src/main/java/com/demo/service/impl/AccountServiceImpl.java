package com.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.demo.dto.AccountDto;
import com.demo.entity.Account;
import com.demo.mapper.AccountMapper;
import com.demo.repository.AccountRepository;
import com.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    // Constructor-based Dependency Injection
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapTOAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        // Correctly store the fetched account entity
        Account account = accountRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Account does not exist"));
        
        // Map to DTO and return
        return AccountMapper.mapToAccountDto(account);
    }

	@Override
	public AccountDto deposit(Long id, double amount) {
		
	        Account account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exist"));
	   double total=account.getBalance()+ amount;
	        account.setBalance(total);
	        Account savedAccount= accountRepository.save(account);
		return  AccountMapper.mapToAccountDto(savedAccount); 
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		 Account account = accountRepository
		            .findById(id)
		            .orElseThrow(() -> new RuntimeException("Account does not exist"));
		        if(account.getBalance()<amount) {
		        	throw new RuntimeException("Insufficient amount");
		        }
		        
		        double total=account.getBalance()- amount;
		        account.setBalance(total);
		      Account savedAccount= accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accounts=accountRepository.findAll();
		
		return accounts.stream()
			    .map(AccountMapper::mapToAccountDto) // Corrected lambda syntax
			    .collect(Collectors.toList());

		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		           accountRepository.deleteById(id);
	}
}

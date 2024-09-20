package com.demo.mapper;

import com.demo.dto.AccountDto;
import com.demo.entity.Account;

public class AccountMapper {
	
	
		public static Account mapTOAccount(AccountDto accountDto) {
			
			Account account =new Account(
					accountDto.getId(),
					accountDto.getAccountHolderName(),
					accountDto.getBalance()
					);
			return account;
		}
		
		
		public static  AccountDto mapToAccountDto(Account account) {
			AccountDto accountDto =new AccountDto(
					account.getId(),
					account.getAccountHolderName(),
					account.getBalance()
					
					
					
					);
			
			return accountDto;
		}

}

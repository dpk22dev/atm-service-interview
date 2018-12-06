package com.example.atm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atm.entity.AccountEntity;
import com.example.atm.entity.AccountSummaryEntity;
import com.example.atm.repository.AccountRepository;
import com.example.atm.repository.AccountSummaryRepository;

@Service
public class AccountService {
	@Autowired 
	private AccountRepository accountRepository; 
	
	@Autowired
	private AccountSummaryRepository accountSummaryRepository;
	
	public synchronized String deposit( String accountNumber, Long amount ) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);

		if (ease == null) {
			return "account number doesn't exist in table";
		}
		AccountEntity ae = new AccountEntity();
		ae.setAccountNumber(accountNumber);
		ae.setAmount(amount);
		ae.setTransType('D');
		accountRepository.save(ae);

		Long curAmount = ease.getAmount();
		ease.setAmount(curAmount + amount);
		accountSummaryRepository.save(ease);

		return "balance udpated";
	}
	
	public synchronized String withdraw( String accountNumber, Long amount  ) {
		   AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);
	        if( ease == null ) {
	        	return "account number doesn't exist in table";
	        }
	        Long curAmount = ease.getAmount();
	        if( curAmount - amount > 0 ) {
		        AccountEntity ae = new AccountEntity();
		        ae.setAccountNumber(accountNumber);
		        ae.setAmount(amount);
		        ae.setTransType('W');
		        accountRepository.save( ae );
		        
		        
		        ease.setAmount( curAmount - amount );
		        accountSummaryRepository.save( ease );
		        return "amount updated";
	        } else {
	        	return "current balance is less!";
	        }
	}
}

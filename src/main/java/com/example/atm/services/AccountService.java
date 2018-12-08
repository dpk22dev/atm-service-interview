package com.example.atm.services;

import java.math.BigDecimal;

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
	
	public synchronized String deposit( Long accountNumber, BigDecimal amount ) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);

		if (ease == null) {
			return "account number doesn't exist in table";
		}
		AccountEntity ae = new AccountEntity();
		ae.setAccountNumber(accountNumber);
		ae.setAmount(amount);
		ae.setTransType('D');
		accountRepository.save(ae);

		BigDecimal curAmount = ease.getAmount();
		curAmount = curAmount.add( amount );
		ease.setAmount( curAmount );
		accountSummaryRepository.save(ease);

		return "balance udpated";
	}
	
	public synchronized String withdraw( Long accountNumber, BigDecimal amount  ) {
		   AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);
	        if( ease == null ) {
	        	return "account number doesn't exist in table";
	        }
	        BigDecimal curAmount = ease.getAmount();
	        
	        if( curAmount.subtract(amount).longValueExact() > 0 ) {
		        AccountEntity ae = new AccountEntity();
		        ae.setAccountNumber(accountNumber);
		        ae.setAmount(amount);
		        ae.setTransType('W');
		        accountRepository.save( ae );
		        
		        curAmount = curAmount.subtract( amount );
		        ease.setAmount( curAmount );
		        accountSummaryRepository.save( ease );
		        return "amount updated";
	        } else {
	        	return "current balance is less!";
	        }
	}

	public String create(Long acc, BigDecimal amount) {
		// TODO Auto-generated method stub
		AccountEntity ae = new AccountEntity();
        ae.setAccountNumber(acc);
        ae.setAmount(amount);
        ae.setTransType('C');
        accountRepository.save( ae );
        
        AccountSummaryEntity ase = new AccountSummaryEntity();
        ase.setAccountNumber(acc);
        ase.setAmount(amount);
        accountSummaryRepository.save(ase);
        return "Account Created with number:" + acc;
	}

	public String getBalance(Long acc) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(acc);
		if( ease == null ) {
        	return "account number doesn't exist in table";
        }
		BigDecimal bd = ease.getAmount();
		return "Current balance for account#" + ease.getAccountNumber() + " is: " + ease.getAmount();
	}
}

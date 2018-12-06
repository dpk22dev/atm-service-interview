package com.example.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm.entity.AccountEntity;
import com.example.atm.entity.AccountSummaryEntity;
import com.example.atm.repository.AccountRepository;
import com.example.atm.repository.AccountSummaryRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountRepository accountRepository; 
	
	@Autowired
	private AccountSummaryRepository accountSummaryRepository;
	
	@GetMapping("/amount")
	public String check( @RequestParam(name="amount", required=true, defaultValue="") Long amount) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAmount(amount);
		if( ease == null ) {
        	return "account number doesn't exist in table";
        }
		return "ok";
	}
	
	@PostMapping("/deposit")
    public synchronized String deposit(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
        
        AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);
        
        if( ease == null ) {
        	return "account number doesn't exist in table";
        }
        AccountEntity ae = new AccountEntity();
        ae.setAccountNumber(accountNumber);
        ae.setAmount(amount);
        ae.setTransType('D');
        accountRepository.save( ae );

        Long curAmount = ease.getAmount();
        ease.setAmount( curAmount + amount );
        accountSummaryRepository.save( ease );
                
        return "balance udpated";
    }		
	
	@PostMapping("/withdraw")
	public synchronized String withdraw(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
		
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

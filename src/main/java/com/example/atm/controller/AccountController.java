package com.example.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm.entity.AccountEntity;
import com.example.atm.entity.PinEntity;
import com.example.atm.repository.AccountRepository;
import com.example.atm.repository.PinRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountRepository accountRepository; 
	
	@PostMapping("/deposit")
    public String deposit(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
        AccountEntity ae = new AccountEntity();
        ae.setAccountNumber(accountNumber);
        ae.setAmount(amount);
        ae.setTransType('D');
        accountRepository.save( ae );
        return "ok";
    }		
	
	@PostMapping("/withdraw")
	public String withdraw(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
        AccountEntity ae = new AccountEntity();
        ae.setAccountNumber(accountNumber);
        ae.setAmount(amount);
        ae.setTransType('W');
        accountRepository.save( ae );
        return "ok";
    }			
	
}

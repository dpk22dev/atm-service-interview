package com.example.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.atm.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;	
	
	@PostMapping("/deposit")
    public String deposit(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
		return accountService.deposit(accountNumber, amount);
               
    }		
	
	@PostMapping("/withdraw")
	public String withdraw(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") Long amount) {
		return accountService.withdraw(accountNumber, amount);             
    }			
	
}

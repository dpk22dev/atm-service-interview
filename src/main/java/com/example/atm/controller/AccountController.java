package com.example.atm.controller;

import java.math.BigDecimal;
import java.util.Random;

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
    public String deposit(@RequestParam(name="accountNumber", required=true, defaultValue="") Long accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") BigDecimal amount) {
		return accountService.deposit(accountNumber, amount);
               
    }		
	
	@PostMapping("/withdraw")
	public String withdraw(@RequestParam(name="accountNumber", required=true, defaultValue="") Long accountNumber, 
    		@RequestParam(name="amount", required=true, defaultValue="") BigDecimal amount) {
		return accountService.withdraw(accountNumber, amount);             
    }			
	
	
	@GetMapping("/create")
	public String createAccount() {
		Long min = 1l; Long max = 10000000000l;
		Random rl = new Random( );
		Long acc = rl.nextInt( (int) (max - min) ) + min;
		return accountService.create( acc, new BigDecimal("0") );		
    }		
	
	@GetMapping("/balance")
	public String getBalance( @RequestParam(name="accountNumber", required=true, defaultValue="") Long acc ) {
		return accountService.getBalance( acc );		
    }
	
}

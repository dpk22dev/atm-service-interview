package com.example.atm.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atm.component.ServiceResponse;
import com.example.atm.controller.AccountController;
import com.example.atm.entity.AccountEntity;
import com.example.atm.entity.AccountSummaryEntity;
import com.example.atm.exceptions.AccountNotFoundException;
import com.example.atm.exceptions.ApplicationException;
import com.example.atm.exceptions.InsufficientBalanceException;
import com.example.atm.repository.AccountRepository;
import com.example.atm.repository.AccountSummaryRepository;
import com.example.atm.utils.ResponseToServiceResponseAdapter;

@Service
public class AccountService {
	@Autowired 
	private AccountRepository accountRepository; 
	
	@Autowired
	private AccountSummaryRepository accountSummaryRepository;
	
	private Logger logger = LoggerFactory.getLogger( AccountService.class);
	
	public synchronized ServiceResponse deposit( Long accountNumber, BigDecimal amount ) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);

		if (ease == null) {
			logger.warn("account doesn't exist");
			return ResponseToServiceResponseAdapter.toServiceResponse( new ApplicationException("Account doesn't exist" ) );
		}
		AccountEntity ae = new AccountEntity();
		ae.setAccountNumber(accountNumber);
		ae.setAmount(amount);
		ae.setTransType('D');
		

		BigDecimal curAmount = ease.getAmount();
		curAmount = curAmount.add( amount );
		ease.setAmount( curAmount );
		
		accountRepository.save(ae);
		accountSummaryRepository.save(ease);
		
		return ResponseToServiceResponseAdapter.toServiceResponse( "balance udpated" );
	}
	
	public synchronized ServiceResponse withdraw( Long accountNumber, BigDecimal amount  ) {
		   AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(accountNumber);
	        if( ease == null ) {
	        	logger.warn("account doesn't exist");
	        	return ResponseToServiceResponseAdapter.toServiceResponse( new AccountNotFoundException( "account number doesn't exist in table" ) );
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
		        return ResponseToServiceResponseAdapter.toServiceResponse( "amount updated" );
	        } else {
	        	logger.warn("attempt to withdraw from insufficient balance");
	        	return ResponseToServiceResponseAdapter.toServiceResponse( new InsufficientBalanceException( "current balance is less!" ) );
	        }
	}

	public ServiceResponse<?> create(Long acc, BigDecimal amount) {
		// TODO Auto-generated method stub
		AccountEntity ae = new AccountEntity();
        ae.setAccountNumber(acc);
        ae.setAmount(amount);
        ae.setTransType('C');
                
        AccountSummaryEntity ase = new AccountSummaryEntity();
        ase.setAccountNumber(acc);
        ase.setAmount(amount);
        
        try {
	        accountRepository.save( ae );
	        accountSummaryRepository.save(ase);
        }catch( Exception e ) {
        	ResponseToServiceResponseAdapter.toServiceResponse( new ApplicationException("Internal service Error", e ) );
        }
        return ResponseToServiceResponseAdapter.toServiceResponse( "Account Created with number:" + acc );
	}

	public ServiceResponse getBalance(Long acc) {
		AccountSummaryEntity ease = accountSummaryRepository.findByAccountNumber(acc);
		if( ease == null ) {
        	//return "account number doesn't exist in table";
			return ResponseToServiceResponseAdapter.toServiceResponse( new ApplicationException("Account doesn't exist" ) );
        }
		BigDecimal bd = ease.getAmount();
		return ResponseToServiceResponseAdapter.toServiceResponse( "Current balance for account#" + ease.getAccountNumber() + " is: " + bd );
	}
}

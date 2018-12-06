package com.example.atm.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.atm.entity.AccountSummaryEntity;

public interface AccountSummaryRepository extends CrudRepository<AccountSummaryEntity, Long> {	 
	AccountSummaryEntity findByAccountNumber( String accountNumber );
	AccountSummaryEntity findByAmount(Long amount);	  
}

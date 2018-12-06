package com.example.atm.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.atm.entity.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {	 
	  
}

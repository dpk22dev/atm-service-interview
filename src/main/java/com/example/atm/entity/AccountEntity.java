package com.example.atm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class AccountEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
		
	private Long accountNumber;
	private BigDecimal amount;
    private Character transType;
    
    public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public Character getTransType() {
		return transType;
	}
	public void setTransType(Character transType) {
		this.transType = transType;
	}        
    
}

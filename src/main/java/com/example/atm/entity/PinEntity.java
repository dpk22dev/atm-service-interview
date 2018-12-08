package com.example.atm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class PinEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private Long accountNumber;
    private String pin;
    
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getPin() {
		return pin;
	}
	/*public PinEntity(Integer id, String accountNumber, String pin) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.pin = pin;
	}*/
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
}

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
	private String accountNumber;
    private String pin;
    
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
}

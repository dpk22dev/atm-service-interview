package com.example.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm.entity.PinEntity;
import com.example.atm.repository.PinRepository;

@RestController
public class PinController {
	
	@Autowired 
	private PinRepository pinRepository; 
	
	@GetMapping("/pin")
    public String updatePin(@RequestParam(name="accountNumber", required=true, defaultValue="") Long accountNumber, 
    		@RequestParam(name="pin", required=true, defaultValue="") String pin) {
        PinEntity pe = new PinEntity();
        pe.setAccountNumber(accountNumber);pe.setPin(pin);
        pinRepository.save( pe );
        return "ok";
    }		
	
}
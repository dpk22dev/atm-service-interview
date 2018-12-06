package com.example.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.atm.entity.PinEntity;
import com.example.atm.repository.PinRepository;

@Controller
public class PinController {
	
	@Autowired 
	private PinRepository pinRepository; 
	
	@PutMapping("/pin")
    public String updatePin(@RequestParam(name="accountNumber", required=true, defaultValue="") String accountNumber, 
    		@RequestParam(name="pin", required=true, defaultValue="") String pin) {
        PinEntity pe = new PinEntity();
        pe.setAccountNumber(accountNumber);pe.setPin(pin);
        pinRepository.save( pe );
        return "ok";
    }
	
	
	
}
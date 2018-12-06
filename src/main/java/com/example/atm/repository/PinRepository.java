package com.example.atm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.atm.entity.PinEntity;

public interface PinRepository extends CrudRepository<PinEntity, Long> {	 
	  	  
}

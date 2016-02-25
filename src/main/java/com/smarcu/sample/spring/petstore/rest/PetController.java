package com.smarcu.sample.spring.petstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarcu.sample.spring.petstore.model.Pet;
import com.smarcu.sample.spring.petstore.repository.PetRepository;

@RestController
public class PetController {

	@Autowired
	private PetRepository petRepository;
	
	@RequestMapping("/pet/{id}")
	public Pet getPet(@PathVariable Long id) {
		
		return petRepository.findOne(id);
		
	}
	
}

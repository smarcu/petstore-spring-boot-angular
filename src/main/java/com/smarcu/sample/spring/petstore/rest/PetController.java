package com.smarcu.sample.spring.petstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarcu.sample.spring.petstore.model.Pet;
import com.smarcu.sample.spring.petstore.model.PetStatus;
import com.smarcu.sample.spring.petstore.repository.PetRepository;

@RestController
public class PetController {

	@Autowired
	private PetRepository petRepository;
	
	/**
	 * Get a pet by id
	 * @param id
	 * @return
	 */
	@RequestMapping("/pet/{id}")
	public Pet getPet(@PathVariable Long id) {
		Pet pet = petRepository.findOne(id);
		if (pet == null) {
			throw new ResourceNotFoundException();
		} else {
			return pet;
		}
	}
	
	@RequestMapping(path = "/pet", method = RequestMethod.POST)
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
		if (!validatePet(pet)) {
			throw new RuntimeException("invalid pet");
		}
		Pet savedPet = petRepository.save(pet);
		return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
	}
	
	private boolean validatePet(Pet pet) {
		// validation
		return true;
	}
	
}

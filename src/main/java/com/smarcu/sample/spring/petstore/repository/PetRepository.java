package com.smarcu.sample.spring.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarcu.sample.spring.petstore.model.Pet;

/**
 * JPA repository for Pet objects
 *
 */
public interface PetRepository extends JpaRepository<Pet, Long>{

}

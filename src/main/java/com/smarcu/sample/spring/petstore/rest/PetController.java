package com.smarcu.sample.spring.petstore.rest;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarcu.sample.spring.petstore.model.Category;
import com.smarcu.sample.spring.petstore.model.Pet;
import com.smarcu.sample.spring.petstore.model.PetStatus;
import com.smarcu.sample.spring.petstore.model.Tag;

@RestController
public class PetController {

	@RequestMapping("/pet/{id}")
	public Pet getPet(Integer id) {
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("pet1");
		pet.setCategory(new Category(1, "category1"));
		pet.setStatus(PetStatus.AVAILABLE);
		pet.setPhotoUrls(Arrays.asList("url1", "url2"));
		pet.setTags(Arrays.asList(new Tag(1, "tag1"), new Tag(2, "tag2")));
		return pet;
	}
	
}

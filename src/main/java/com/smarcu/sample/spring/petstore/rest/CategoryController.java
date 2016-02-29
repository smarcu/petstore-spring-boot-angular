package com.smarcu.sample.spring.petstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarcu.sample.spring.petstore.model.Category;
import com.smarcu.sample.spring.petstore.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/category/all")
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@RequestMapping(path = "/category", method = RequestMethod.POST)
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category savedCategory = categoryRepository.save(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
}
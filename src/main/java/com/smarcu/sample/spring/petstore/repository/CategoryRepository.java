package com.smarcu.sample.spring.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarcu.sample.spring.petstore.model.Category;

/**
 * JPA repository for Category objects
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

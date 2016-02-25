package com.smarcu.sample.spring.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarcu.sample.spring.petstore.model.Tag;

/**
 * JPA repository for Tag objects
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long>{

}

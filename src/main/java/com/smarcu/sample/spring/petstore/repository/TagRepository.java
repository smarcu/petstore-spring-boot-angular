package com.smarcu.sample.spring.petstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarcu.sample.spring.petstore.model.Tag;

/**
 * JPA repository for Tag objects
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long>{

	Tag findByName(String name);
	
	default List<Tag> save(List<Tag> tags) {
		List<Tag> result = new ArrayList<>();
		for (Tag tag : tags) {
			Tag findByName = findByName(tag.getName());
			if (findByName != null) {
				result.add(findByName);
			} else {
				result.add(save(tag));
			}
		}
		return result;
	}
	
}

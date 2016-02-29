package com.smarcu.sample.spring.petstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 * Pet entity
 *
 */
@Entity
public class Pet {
	
	@Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne(optional=true, cascade={CascadeType.MERGE})
	private Category category;
	
	private String name;
	
	@ElementCollection
	private List<String> photoUrls;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Tag> tags;
	
	private PetStatus status;
	
	public Pet() {
		super();
	}

	public Pet(Long id, Category category, String name, List<String> photoUrls, List<Tag> tags, PetStatus status) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public PetStatus getStatus() {
		return status;
	}

	public void setStatus(PetStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", category=" + category + ", name=" + name + ", photoUrls=" + photoUrls + ", tags="
				+ tags + ", status=" + status + "]";
	}

}

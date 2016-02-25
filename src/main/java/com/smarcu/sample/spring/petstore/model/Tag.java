package com.smarcu.sample.spring.petstore.model;

/**
 * A tag entity
 */
public class Tag {
	
	private int id;
	private String name;

	public Tag() {
		super();
	}

	public Tag(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

}

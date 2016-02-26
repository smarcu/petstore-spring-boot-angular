package com.smarcu.sample.spring.petstore.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarcu.sample.spring.petstore.PetstoreApplication;
import com.smarcu.sample.spring.petstore.config.WebSecurityConfig;
import com.smarcu.sample.spring.petstore.model.Category;
import com.smarcu.sample.spring.petstore.model.Pet;
import com.smarcu.sample.spring.petstore.model.PetStatus;
import com.smarcu.sample.spring.petstore.model.Tag;
import com.smarcu.sample.spring.petstore.repository.CategoryRepository;
import com.smarcu.sample.spring.petstore.repository.PetRepository;
import com.smarcu.sample.spring.petstore.repository.TagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PetstoreApplication.class, WebSecurityConfig.class})
@WebAppConfiguration
public class PetControllerTest {

	private MediaType jsonContentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private PetRepository petRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TagRepository tagRepository;
	
	private ObjectMapper jsonObjMapper;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Pet pet;
	private Category category;
	private List<Tag> tags;


	@Before
	public void setup() throws Exception {
		
		this.jsonObjMapper = new ObjectMapper();
		
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		this.petRepository.deleteAll();
		this.categoryRepository.deleteAll();
		this.tagRepository.deleteAll();
		
		this.category = this.categoryRepository.save(new Category(1L, "category1"));
		this.tags = new ArrayList<>();
		this.tags.add( this.tagRepository.save(new Tag(1L, "tag1")));
		this.tags.add( this.tagRepository.save(new Tag(2L, "tag2")));
		
		this.pet = this.petRepository.save(new Pet(null, this.category, "pet1", 
				Arrays.asList("url1", "url2"), 
				this.tags, PetStatus.AVAILABLE));
	}

	@Test
	public void getPet() throws Exception {
		mockMvc.perform(get("/pet/"+this.pet.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(jsonContentType))
				.andExpect(jsonPath("$.id", is(this.pet.getId().intValue())))
				.andExpect(jsonPath("$.name", is("pet1")))
				.andExpect(jsonPath("$.category.id", is(this.category.getId().intValue())))
				.andExpect(jsonPath("$.category.name", is("category1")))
				.andExpect(jsonPath("$.photoUrls.length()", is(2)))
				.andExpect(jsonPath("$.photoUrls[0]", is("url1")))
				.andExpect(jsonPath("$.photoUrls[1]", is("url2")))
				.andExpect(jsonPath("$.tags.length()", is(2)))
				.andExpect(jsonPath("$.tags[0].id", is(this.tags.get(0).getId().intValue())))
				.andExpect(jsonPath("$.tags[0].name", is("tag1")))
				.andExpect(jsonPath("$.tags[1].id", is(this.tags.get(1).getId().intValue())))
				.andExpect(jsonPath("$.tags[1].name", is("tag2")))
				.andExpect(jsonPath("$.status", is("AVAILABLE")))
				;
	}
	
	@Test
	public void getPet_NotFound() throws Exception {
		mockMvc.perform(get("/pet/100"))
				.andExpect(status().is(404));
	}
	
	@Test
	public void addPet() throws Exception {
		String jsonPet = json(new Pet(null, this.category, "newPet", Arrays.asList("urlx"), this.tags, PetStatus.NOT_AVAILABLE));
		mockMvc.perform(post("/pet")
						.contentType(jsonContentType)
						.content(jsonPet))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("newPet")))
				.andExpect(jsonPath("$.category.id", is(this.category.getId().intValue())))
				.andExpect(jsonPath("$.category.name", is("category1")))
				.andExpect(jsonPath("$.photoUrls.length()", is(1)))
				.andExpect(jsonPath("$.photoUrls[0]", is("urlx")))
				.andExpect(jsonPath("$.tags.length()", is(2)))
				.andExpect(jsonPath("$.tags[0].id", is(this.tags.get(0).getId().intValue())))
				.andExpect(jsonPath("$.tags[0].name", is("tag1")))
				.andExpect(jsonPath("$.tags[1].id", is(this.tags.get(1).getId().intValue())))
				.andExpect(jsonPath("$.tags[1].name", is("tag2")))
				.andExpect(jsonPath("$.status", is("NOT_AVAILABLE")))
				;		
	}
	
	@Test
	public void addPet_NoContent() throws Exception {
		mockMvc.perform(post("/pet")
						.contentType(jsonContentType)
						.content(""))
				.andExpect(status().is(400))
				;		
	}
	
	@Test
	public void deletePet() throws Exception {
		mockMvc.perform(delete("/pet/"+this.pet.getId())).andExpect(status().isAccepted());
		
		Pet deletedPet = this.petRepository.findOne(this.pet.getId());
		
		Assert.assertNull(deletedPet);
	}
	
	@Test
	public void deletePet_NotFound() throws Exception {
		mockMvc.perform(delete("/pet/123")).andExpect(status().isNotFound());
		
		Pet deletedPet = this.petRepository.findOne(this.pet.getId());
		
		Assert.assertNotNull(deletedPet);
	}

	
	protected String json(Object o) throws IOException {
		return this.jsonObjMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
	}
}

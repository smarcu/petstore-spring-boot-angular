package com.smarcu.sample.spring.petstore.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.smarcu.sample.spring.petstore.PetstoreApplication;
import com.smarcu.sample.spring.petstore.model.Category;
import com.smarcu.sample.spring.petstore.model.Pet;
import com.smarcu.sample.spring.petstore.model.PetStatus;
import com.smarcu.sample.spring.petstore.model.Tag;
import com.smarcu.sample.spring.petstore.repository.CategoryRepository;
import com.smarcu.sample.spring.petstore.repository.PetRepository;
import com.smarcu.sample.spring.petstore.repository.TagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PetstoreApplication.class)
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
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Pet pet;
	private Category category;
	private List<Tag> tags;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays
				.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		this.categoryRepository.deleteAllInBatch();
		this.tagRepository.deleteAllInBatch();
		this.petRepository.deleteAllInBatch();
		
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
		mockMvc.perform(get("/pet/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(jsonContentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("pet1")))
				.andExpect(jsonPath("$.category.id", is(1)))
				.andExpect(jsonPath("$.category.name", is("category1")))
				.andExpect(jsonPath("$.photoUrls.length()", is(2)))
				.andExpect(jsonPath("$.photoUrls[0]", is("url1")))
				.andExpect(jsonPath("$.photoUrls[1]", is("url2")))
				.andExpect(jsonPath("$.tags.length()", is(2)))
				.andExpect(jsonPath("$.tags[0].id", is(1)))
				.andExpect(jsonPath("$.tags[0].name", is("tag1")))
				.andExpect(jsonPath("$.tags[1].id", is(2)))
				.andExpect(jsonPath("$.tags[1].name", is("tag2")))
				.andExpect(jsonPath("$.status", is("AVAILABLE")))
				;
	}
}

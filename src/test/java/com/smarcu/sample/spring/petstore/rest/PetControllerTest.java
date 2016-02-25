package com.smarcu.sample.spring.petstore.rest;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.smarcu.sample.spring.petstore.PetstoreApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PetstoreApplication.class)
@WebAppConfiguration
public class PetControllerTest {

	private MediaType jsonContentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

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
				.andExpect(jsonPath("$.category.id", is(1)))
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

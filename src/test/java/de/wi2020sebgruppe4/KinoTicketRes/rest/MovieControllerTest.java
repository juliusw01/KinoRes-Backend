package de.wi2020sebgruppe4.KinoTicketRes.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.wi2020sebgruppe4.KinoTicketRes.model.Movie;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.MovieRepository;
import de.wi2020sebgruppe4.KinoTicketRes.repositories.ShowRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	MockMvc mvc;
	
	@MockBean
	MovieRepository repo;
	
	@MockBean
	ShowRepository showRepository;
	
	@Autowired
    WebApplicationContext wac;
	
	JacksonTester<Movie> jt;
	
	static UUID uuid;
	
	@BeforeAll
	static void beforeAll() {
		uuid = new UUID(2, 2);
	}
	
	@BeforeEach
    void beforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	 Movie getMovie() {
		    Movie m = new Movie("Star Wars", "German", 120, "George Lucas", "Weltraum ist cool", new Date(2015-03-31), "https://picute.link", "https://trailer-link", false, 12);
	    	m.setId(uuid);
	    	return m;
	}
	 
	@Test
    void testGetAll() throws Exception {
    	when(repo.findAll()).thenReturn(new ArrayList<Movie>());
        mvc.perform(get("/movies"))
                .andExpect(status().isOk());
    }
}

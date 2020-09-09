package com.cognixia.jump.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;




@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
	
	private final String STARTING_URI="http://localhost:8080/api";
	
	RestaurantController controller;
	
	@MockBean
	private RestaurantRepository repo;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllRestaurants() throws Exception {
		
		String uri=STARTING_URI+"/restaurants";
		
		List<Restaurant> allRestaurants=Arrays.asList(
				 new Restaurant(1L, "French Quarter", "New Orleans", "La", 70707L, "Cafe Call", "Beignet", "French"),
				 new Restaurant(2L, "College Dr", "Baton Rouge", "La", 70907L, "Cafe Joy", "Borek", "Turkish")
				);
		
		when( repo.findAll() ).thenReturn(allRestaurants);
		
		mockMvc.perform(get(uri))
		       .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.length()").value(allRestaurants.size()))
		       .andExpect(jsonPath("$[0].city").value(allRestaurants.get(0).getCity()))
		       .andExpect(jsonPath("$[1].foodType").value(allRestaurants.get(1).getFoodType()));
		
		
		verify(repo,times(1)).findAll();
		verifyNoMoreInteractions(repo);
		       
		
		
	}
	
	@Test
	void testGetOneRestaurantById() throws Exception {
		String uri=STARTING_URI+"/restaurants/{id}";
		long id=1;
		
		Restaurant restaurant=new Restaurant(id, "fairbanks st", "tx", "el paso", 79938L, "Taco Bell", "tacos", "Mexican");
	    
		when(repo.findById(id)).thenReturn(Optional.of(restaurant));
		
		mockMvc.perform(get(uri,id))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.zipCode").value(restaurant.getZipCode()))
		       .andExpect(jsonPath("$.city").value(restaurant.getCity()))
		       .andExpect(jsonPath("$.foodType").value(restaurant.getFoodType()));
		       
		verify(repo,times(1)).findById(id);
		verifyNoMoreInteractions(repo);
	
	}
	
	@Test
	void testAddRestaurant() throws Exception {
		String uri=STARTING_URI+"/add/restaurant";
		
		Restaurant restaurantAdded=new Restaurant(2L, "florida bld.", "LA", "baton rouge", 70838L, "Sultan Kebab", "kebab", "Arabic");
		
		when(repo.save(Mockito.any(Restaurant.class))).thenReturn(restaurantAdded);
		
		mockMvc.perform(post(uri)
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(asJsonString(restaurantAdded)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.restaurantId").value(restaurantAdded.getRestaurantId()));
				        
				
		verify(repo,times(1)).save(Mockito.any(Restaurant.class));
		verifyNoMoreInteractions(repo);
	}
	
	
	@Test
	void testUpdateRestaurant() throws Exception {
		String uri=STARTING_URI+"/update/restaurant";
		long id=1L;
		
		Restaurant restaurantUpdated=new Restaurant(id, "florida bld.", "LA", "baton rouge", 70838L, "Sultan Kebab", "kebab", "Arabic");
	
	  when(repo.existsById(id)).thenReturn(true);
	  when(repo.save(Mockito.any(Restaurant.class))).thenReturn(restaurantUpdated);
	  
	  mockMvc.perform(put(uri)
			          .contentType(MediaType.APPLICATION_JSON)
			          .content(asJsonString(restaurantUpdated)))
	         .andExpect(status().isOk())
	         .andExpect(jsonPath("$.restaurantId").value(restaurantUpdated.getRestaurantId()));
	  
	  verify(repo,times(1)).existsById(id);
	  verify(repo,times(1)).save(Mockito.any(Restaurant.class));
	  verifyNoMoreInteractions(repo);
	
	}
	
	@Test
	void testDeleteRestaurant() throws Exception {
		String uri=STARTING_URI+"/delete/restaurant/{id}";
		long id=1L;
		
		Restaurant restaurantDeleted=new Restaurant(id, "florida bld.", "LA", "baton rouge", 70838L, "Sultan Kebab", "kebab", "Arabic");
		
		when(repo.existsById(id)).thenReturn(true);
		when(repo.findById(id)).thenReturn(Optional.of(restaurantDeleted));
		doNothing().when(repo).deleteById(id);
		
		mockMvc.perform( delete(uri,id)
		          .contentType( MediaType.APPLICATION_JSON)
		          .content( asJsonString(restaurantDeleted)))
	   .andDo(print())
       .andExpect( status().isOk());
       

		verify(repo,times(1)).deleteById(id);
		
		
		

		
		
	}
	
public static String asJsonString(final Object obj) {
		
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException();
		}
		
	}

}

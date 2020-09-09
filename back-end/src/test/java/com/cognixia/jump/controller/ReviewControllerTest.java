package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.fail;
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

import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";
	
	@MockBean
	private ReviewRepository repo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetAllReviews() throws Exception {
		
		String uri = STARTING_URI + "/reviews";
				
		List<Review> allReviews = Arrays.asList(
				new Review(-1L, "good comment", 4, 1L, 2L),
				new Review(-2L, "bad comment", 2, 2L, 4L)
		);
		
		when(repo.findAll()).thenReturn(allReviews);
		
		mockMvc.perform(get(uri))
				.andDo(print())  
				.andExpect(status().isOk()) 
				.andExpect(jsonPath("$.length()").value(allReviews.size()))
				.andExpect(jsonPath("$[0].reviewId").value(allReviews.get(0).getReviewId()))
				.andExpect(jsonPath("$[0].comment").value(allReviews.get(0).getComment()))
				.andExpect(jsonPath("$[0].noStars").value(allReviews.get(0).getNoStars()))
				.andExpect(jsonPath("$[0].restaurantId").value(allReviews.get(0).getRestaurantId()))
				.andExpect(jsonPath("$[0].userId").value(allReviews.get(0).getUserId())); 
				
		verify(repo, times(1)).findAll(); 
		verifyNoMoreInteractions(repo); 
	}

	@Test
	void testGetReview() throws Exception{
		
		String uri = STARTING_URI + "/reviews/{id}";
		
		long id = 1L;
		Review review = new Review(id, "good comment", 4, 1L, 2L);
		
		when(repo.findById(id)).thenReturn(Optional.of(review));
		
		mockMvc.perform(get(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.reviewId").value(review.getReviewId()))
			.andExpect(jsonPath("$.comment").value(review.getComment()))
			.andExpect(jsonPath("$.noStars").value(review.getNoStars()))
			.andExpect(jsonPath("$.restaurantId").value(review.getRestaurantId()))
			.andExpect(jsonPath("$.userId").value(review.getUserId())); 
		
		verify(repo, times(1)).findById(id);
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testAddReview() throws Exception{
		
		String uri = STARTING_URI + "/add/review";
		
		Review review = new Review(1L, "good comment", 4, 1L, 2L);
		
		when( repo.save( Mockito.any(Review.class) ) ).thenReturn(review);
		
		mockMvc.perform( post(uri)
						.contentType( MediaType.APPLICATION_JSON ) 
						.content( asJsonString(review) ) )
				.andExpect( status().isCreated() )
				.andDo(print() )
				.andExpect(jsonPath("$.reviewId").value(review.getReviewId()))
				.andExpect(jsonPath("$.comment").value(review.getComment()))
				.andExpect(jsonPath("$.noStars").value(review.getNoStars()))
				.andExpect(jsonPath("$.restaurantId").value(review.getRestaurantId()))
				.andExpect(jsonPath("$.userId").value(review.getUserId()));
		
		verify(repo, times(1)).save(Mockito.any(Review.class));
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testUpdateReview() throws Exception{
		
		String uri = STARTING_URI + "/update/review";
		long id = 1L;
		
		Review review = new Review(id, "good comment", 4, 1L, 2L);
		
		when( repo.existsById(id) ).thenReturn(true);
		when( repo.save(Mockito.any(Review.class)) ).thenReturn(review);
		
		mockMvc.perform( put(uri)
						 .contentType(MediaType.APPLICATION_JSON)
						 .content( asJsonString(review) ))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.reviewId").value(review.getReviewId()))
				.andExpect(jsonPath("$.comment").value(review.getComment()))
				.andExpect(jsonPath("$.noStars").value(review.getNoStars()))
				.andExpect(jsonPath("$.restaurantId").value(review.getRestaurantId()))
				.andExpect(jsonPath("$.userId").value(review.getUserId()));
		
		verify(repo, times(1)).existsById(id);
		verify(repo, times(1)).save(Mockito.any(Review.class));
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testDeleteReview() throws Exception {
		
		String uri = STARTING_URI + "/delete/review/{id}";
		long id = 1L;
		
		Review review = new Review(id, "good comment", 4, 1L, 2L);
		
		when(repo.existsById(id)).thenReturn(true);
		when(repo.findById(id)).thenReturn(Optional.of(review));
		doNothing().when(repo).deleteById(id);
		
		mockMvc.perform(delete(uri, id))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(repo, times(1)).existsById(id);
		verify(repo, times(1)).findById(id);
		verify(repo, times(1)).deleteById(id);
		verifyNoMoreInteractions(repo);
	}
	
	public static String asJsonString(final Object obj) {
		
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException();
		}
		
	}

}

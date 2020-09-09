package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.cognixia.jump.model.Photo;
import com.cognixia.jump.repository.PhotoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PhotoController.class)
class PhotoControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	@MockBean
	private PhotoRepository repo;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllPhotos() throws Exception {

		String uri = STARTING_URI + "/photos";

		List<Photo> allPhotos = Arrays.asList(new Photo(1L, "fakeUrl1", 2L),
				new Photo(2L,"fakeUrl2", 2L));
		System.out.println(allPhotos);

		when(repo.findAll()).thenReturn(allPhotos);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(allPhotos.size()))
				.andExpect(jsonPath("$[0].photoId").value(allPhotos.get(0).getPhotoId()))
				.andExpect(jsonPath("$[0].url").value(allPhotos.get(0).getUrl()))
				.andExpect(jsonPath("$[0].restaurantId").value(allPhotos.get(0).getRestaurantId()));
				
		verify(repo, times(1)).findAll();
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testGetPhoto() throws Exception {

		String uri = STARTING_URI + "/photos/{id}";

		long id = 1L;
		Photo photo = new Photo(id, "fakeUrl/1", 2L);

		when(repo.findById(id)).thenReturn(Optional.of(photo));

		mockMvc.perform(get(uri, id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.photoId").value(photo.getPhotoId()))
				.andExpect(jsonPath("$.url").value(photo.getUrl()))
				.andExpect(jsonPath("$.restaurantId").value(photo.getRestaurantId()));

		verify(repo, times(1)).findById(id);
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testAddPhoto() throws Exception {

		String uri = STARTING_URI + "/add/photo";

		Photo photo = new Photo(1L, "fakeUrl/1", 2L);

		when(repo.save(Mockito.any(Photo.class))).thenReturn(photo);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(photo)))
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(jsonPath("$.photoId").value(photo.getPhotoId()))
				.andExpect(jsonPath("$.url").value(photo.getUrl()))
				.andExpect(jsonPath("$.restaurantId").value(photo.getRestaurantId()));
			
		verify(repo, times(1)).save(Mockito.any(Photo.class));
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testUpdatePhoto() throws Exception {

		String uri = STARTING_URI + "/update/photo";
		long id = 1L;

		Photo photo = new Photo(id, "fakeUrl/1", 2L);

		when(repo.existsById(id)).thenReturn(true);
		when(repo.save(Mockito.any(Photo.class))).thenReturn(photo);

		mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(photo)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.photoId").value(photo.getPhotoId()))
				.andExpect(jsonPath("$.url").value(photo.getUrl()))
				.andExpect(jsonPath("$.restaurantId").value(photo.getRestaurantId()));

		verify(repo, times(1)).existsById(id);
		verify(repo, times(1)).save(Mockito.any(Photo.class));
		verifyNoMoreInteractions(repo);
	}

	@Test
	void testDeletePhoto() throws Exception {

		String uri = STARTING_URI + "/delete/photo/{id}";
		long id = 1L;

		Photo photo = new Photo(id, "fakeUrl/1", 2L);

		when(repo.existsById(id)).thenReturn(true);
		when(repo.findById(id)).thenReturn(Optional.of(photo));
		doNothing().when(repo).deleteById(id);

		mockMvc.perform(delete(uri, id)).andDo(print()).andExpect(status().isOk());

		verify(repo, times(1)).existsById(id);
		verify(repo, times(1)).findById(id);
		verify(repo, times(1)).deleteById(id);
		verifyNoMoreInteractions(repo);
	}

	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}

package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;



@RestController
@RequestMapping("/api")
public class RestaurantController {
	
	
	@Autowired
	RestaurantRepository repository;
	
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants() {
		return repository.findAll();
	}
	
	@GetMapping("/restaurants/{restaurantId}")
    public Restaurant getOneRestaurant(@PathVariable Long restaurantId) throws ResourceNotFoundException {
		Optional<Restaurant>found=repository.findById(restaurantId);
		if (!found.isPresent()) {
			throw new ResourceNotFoundException("Restaurant with id "+ restaurantId + " not found"); 
		}
		return found.get();
	}
	
	@PostMapping("/add/restaurant")
		public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
		   //boolean found=repository.existsById(restaurant.getRestaurantId());
		    restaurant.setRestaurantId(-1L);
			
			Restaurant restaurant2=repository.save(restaurant);
			return ResponseEntity.status(201).body(restaurant2 );
			
			
		}
	
	@DeleteMapping("/delete/restaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) throws ResourceNotFoundException{
	//	Optional<Restaurant> found=repository.findById(restaurantId);
		if (!repository.existsById(restaurantId)) {
			throw new ResourceNotFoundException("Restaurant with id "+ restaurantId + " not found");
			
		}
		   repository.deleteById(restaurantId);
		   return ResponseEntity.status(200).body("Restaurant with " + restaurantId + " has been deleted");
	}
	
	@PutMapping("/update/restaurant")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) throws ResourceNotFoundException{
		 boolean found=repository.existsById(restaurant.getRestaurantId());
		
		 if (!found) {
			throw new ResourceNotFoundException("Restaurant with id "+ restaurant.getRestaurantId() + " not found");
		}
		Restaurant updatedRestaurant=repository.save(restaurant);
		return new ResponseEntity<Restaurant>(updatedRestaurant,HttpStatus.OK);
	}
	
	
	
	
	
	}
	



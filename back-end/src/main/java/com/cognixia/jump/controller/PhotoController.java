package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.cognixia.jump.model.Photo;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Photo;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.PhotoRepository;

@RequestMapping("/api")
@RestController
public class PhotoController {

	@Autowired
	PhotoRepository service;
	

	

	@GetMapping("/photos")
	public List<Photo> getAllPhotos() {
		return service.findAll();
	}
	
	//TODO: add exceptions/handling for wrong inputs
	@GetMapping("/photos/{id}")
	public Photo getPhoto(@PathVariable long id){

		Optional<Photo> photo = service.findById(id);
		
		if(photo.isPresent()) {
			return photo.get();
		}
		
		return new Photo();
	}
	
	@GetMapping("/photos/restaurant/{id}")
	public Optional<List<Photo>> getPhotosbyId(@PathVariable Long id) {
		   Optional<List<Photo>> photos = service.findByRestaurantId(id);
		   return photos;
	}
	
	@PostMapping("/add/photo")
	public ResponseEntity<Photo> addPhoto(@Valid @RequestBody Photo newPhoto){
		
		newPhoto.setPhotoId(-1L);
		Photo created = service.save(newPhoto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/photo")
	public @ResponseBody Photo updatePhoto(@RequestBody Photo photo) throws ResourceNotFoundException {
		
		if( !service.existsById(photo.getPhotoId()) ) {
			throw new ResourceNotFoundException("Review with id = " + photo.getPhotoId() + " not found");
		}
		
		Photo updated = service.save(photo);
		
		return updated;
	}
	
	@DeleteMapping("/delete/photo/{id}")
	public ResponseEntity<Photo> deletePhoto(@PathVariable long id) throws ResourceNotFoundException{
		
		if(!service.existsById(id)) {
			throw new ResourceNotFoundException("Photo with id = " + id + " not found, cannot be deleted");
		}
		
		Photo deleted = service.findById(id).get();
		
		service.deleteById(id);
		
		return new ResponseEntity<>(deleted, HttpStatus.OK);
		
	}

}

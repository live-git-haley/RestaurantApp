package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

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

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;

@RequestMapping("/api")
@RestController
public class ReviewController {

	@Autowired
	ReviewRepository service;

	@GetMapping("/reviews")
	public List<Review> getAllReviews() {
		return service.findAll();
	}

	// TODO: add exceptions/handling for wrong inputs
	@GetMapping("/reviews/{id}")
	public Review getReview(@PathVariable long id) {

		Optional<Review> review = service.findById(id);

		if (review.isPresent()) {
			return review.get();
		}

		return new Review();
	}
	
	@GetMapping("/reviews/restaurant/{id}")
	public Optional<List<Review>> getReviewsbyRestId(@PathVariable Long id) {
		   Optional<List<Review>> reviews = service.findByRestaurantId(id);
		   return reviews;
	}

	@PostMapping("/add/review")
	public ResponseEntity<Review> addReview(@Valid @RequestBody Review newReview) {

		newReview.setReviewId(-1L);
		Review created = service.save(newReview);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping("/update/review")
	public @ResponseBody Review updateReview(@RequestBody Review review) throws ResourceNotFoundException {

		if (!service.existsById(review.getReviewId())) {
			throw new ResourceNotFoundException("Review with id = " + review.getReviewId() + " not found");
		}

		Review updated = service.save(review);

		return updated;
	}

	@DeleteMapping("/delete/review/{id}")
	public ResponseEntity<Review> deleteReview(@PathVariable long id) throws ResourceNotFoundException {

		if (!service.existsById(id)) {
			throw new ResourceNotFoundException("Review with id = " + id + " not found, cannot be deleted");
		}

		Review deleted = service.findById(id).get();

		service.deleteById(id);

		return new ResponseEntity<>(deleted, HttpStatus.OK);

	}

}

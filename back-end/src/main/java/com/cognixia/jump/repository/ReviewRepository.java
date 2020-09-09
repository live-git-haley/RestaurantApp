package com.cognixia.jump.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	Optional<List<Review>> findByRestaurantId(long restaurantId);

}

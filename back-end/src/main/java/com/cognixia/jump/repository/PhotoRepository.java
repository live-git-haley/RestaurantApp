package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	Optional<List<Photo>> findByRestaurantId(long restaurantId);



}

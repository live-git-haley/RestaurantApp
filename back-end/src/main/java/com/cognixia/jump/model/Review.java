package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewId;
	
	@NotNull(message = "Comment can't be null")
	private String comment;
	
	@Range(min=1,max=5)
	private int noStars;
	
	private Long restaurantId;
	
	private Long userId;
	
	public Review() {
		this(-1L, "N/A", -1, -1L, -1L);
	}

	public Review(Long reviewId, String comment, int noStars, Long restaurantId, Long userId) {
		super();
		this.reviewId = reviewId;
		this.comment = comment;
		this.noStars = noStars;
		this.restaurantId = restaurantId;
		this.userId = userId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNoStars() {
		return noStars;
	}

	public void setNoStars(int noStars) {
		this.noStars = noStars;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", comment=" + comment + ", noStars=" + noStars + ", restaurantId="
				+ restaurantId + ", userId=" + userId + "]";
	}
	
}

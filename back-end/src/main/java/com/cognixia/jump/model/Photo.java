package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Photo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long photoId;
	
	@Column(length= 1500)
	private String url;
	
	private Long restaurantId;
	
	public Photo() {
		this(-1L, "N/A", -1L);
	}

	public Photo(Long photoId, String url, Long restaurantId) {
		super();
		this.photoId = photoId;
		this.url = url;
		this.restaurantId = restaurantId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", url=" + url + ", restaurantId=" + restaurantId + "]";
	}
	
}

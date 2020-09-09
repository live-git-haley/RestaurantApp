package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1818570162468403899L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long restaurantId;
	@NotNull(message = "Address number can't be null")
	private String address;
	@Pattern(regexp = "^[A-Z]{2}$")
	@Column(columnDefinition = "char(2) default '--'")
	private String state;
	@Size(min=1,max=20)
	private String city;
	@Column(columnDefinition = "char(5) default '00000'")
	private Long zipCode;
	@NotBlank
	private String name;
	@NotNull
	private String description;
	@NotBlank
	private String foodType;
	
	public Restaurant() {
		this(-1L, "", "--", "", 00000L, "", "", "");
	}
	
	public Restaurant(Long restaurantId, @NotNull(message = "Address number can't be null") String address,
			@Pattern(regexp = "^[A-Z]{2}$") String state, @Size(min = 1, max = 20) String city, Long zipCode,
			@NotBlank String name, @NotNull String description, @NotBlank String foodType) {
		super();
		this.restaurantId = restaurantId;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.name = name;
		this.description = description;
		this.foodType = foodType;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", address=" + address + ", state=" + state + ", city="
				+ city + ", zipCode=" + zipCode + ", name=" + name + ", description=" + description + ", foodType="
				+ foodType + "]";
	}
	
	
	
	
	
	

}

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

import com.cognixia.jump.exceptions.ResourceNotFoundException;
import com.cognixia.jump.model.Admin;
import com.cognixia.jump.repository.AdminRepository;

@RequestMapping("/api")
@RestController
public class AdminController {

	@Autowired
	AdminRepository service;

	@GetMapping("/admin")
	public List<Admin> getAllAdmin() {

		return service.findAll();
	}

	@GetMapping("/admin/{adminId}")
	public Admin getByAdminId(@PathVariable long adminId) throws Exception {

		Optional<Admin> optional = service.findById(adminId);

		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Admin with Id = " + adminId + " not found");
		} else
			return optional.get();

	}

	@PostMapping("/admin/add")
	public String addAdmin(@RequestBody Admin newAdmin) {
		System.out.println(newAdmin);
		service.save(newAdmin);
		return "Added Admin";
	}

	@DeleteMapping("/admin/delete/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable long adminId) {

		Optional<Admin> found = service.findById(adminId);

		if (found.isPresent()) {

			service.deleteById(adminId);
			return ResponseEntity.status(200).body("Deleted admin with id = " + adminId);
		} else {
			return ResponseEntity.status(400).header("admin id", adminId + "")
					.body("Admin with id = " + adminId + " not found");
		}

	}

	@PutMapping("/admin/update")
	public ResponseEntity<Admin> updateRestaurant(@RequestBody Admin admin) throws ResourceNotFoundException {
		Optional<Admin> found = service.findById(admin.getAdminId());
		if (!found.isPresent()) {
			throw new ResourceNotFoundException("Restaurant with id " + admin.getAdminId() + " not found");
		}
		Admin updatedAdmin = service.save(admin);
		return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.ACCEPTED);
	}
}

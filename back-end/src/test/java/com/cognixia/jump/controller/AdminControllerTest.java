package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.controller.AdminController;
import com.cognixia.jump.model.Admin;
import com.cognixia.jump.repository.AdminRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	@MockBean
	private AdminRepository repo;

	AdminController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllAdmin() throws Exception {

		String uri = STARTING_URI + "/admin";

		List<Admin> allAdmin = Arrays.asList(new Admin(-1L, "Pass", "UserName"), new Admin(-1L, "Pass2", "UserName2"));

		when(repo.findAll()).thenReturn(allAdmin);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void testGetOneAdmin() throws Exception {

		Long id = 1L;
		String uri = STARTING_URI + "/admin/" + id;

		Admin admin = new Admin(id, "Pass4", "User4");
		Optional<Admin> opt = Optional.of(admin);

		when(repo.findById(id)).thenReturn(opt);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void testDeleteAdmin() throws Exception {

		Long id = 3L;
		String uri = STARTING_URI + "/admin/delete" + id;

		repo.save(new Admin(id, "Pass3", "User3"));
		repo.deleteById(id);

		verify(repo, times(1)).deleteById(id);

	}

	@Test
	void testAddAdmin() throws Exception {

		Long id = 3L;
		String uri = STARTING_URI + "/admin";

		Admin admin = new Admin(id, "Pass3", "User3");
		Optional<Admin> opt = Optional.of(admin);
		repo.save(opt.get());

		when(repo.findById(id)).thenReturn(opt);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

}

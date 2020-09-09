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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	@MockBean
	private UserRepository repo;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "user1", password = "user1", authorities = { "ROLE_ADMIN" })
	@Test
	void testGetAllUsers() throws Exception {

		String uri = STARTING_URI + "/users";

		List<User> allUsers = Arrays.asList(new User(-1L, "Pass", "UserName", Role.ROLE_USER, true),
				new User(-1L, "Pass2", "User2", Role.ROLE_USER, true));

		when(repo.findAll()).thenReturn(allUsers);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void testGetOneUsers() throws Exception {

		Long id = 1L;
		String uri = STARTING_URI + "/user/" + id;

		User user = new User(id, "Pass4", "User4", Role.ROLE_USER, true);
		Optional<User> opt = Optional.of(user);

		when(repo.findById(id)).thenReturn(opt);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void testDeleteUser() throws Exception {

		Long id = 3L;
		String uri = STARTING_URI + "/user/delete" + id;

		repo.save(new User(id, "Pass3", "User3", Role.ROLE_USER, true));
		repo.deleteById(id);

		verify(repo, times(1)).deleteById(id);

	}

	@WithMockUser(username = "user1", password = "user1", authorities = { "ROLE_ADMIN" })
	@Test
	void testAddUser() throws Exception {

		Long id = 3L;
		String uri = STARTING_URI + "/user/add";

		User user = new User(id, "Pass3", "User3", Role.ROLE_USER, true);
		Optional<User> opt = Optional.of(user);
		repo.save(opt.get());

		when(repo.findById(id)).thenReturn(opt);

		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());

	}

}

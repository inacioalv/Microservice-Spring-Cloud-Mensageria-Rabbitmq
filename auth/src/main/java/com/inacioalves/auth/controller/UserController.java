package com.inacioalves.auth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import com.inacioalves.auth.model.User;
import com.inacioalves.auth.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Void> create(@RequestBody User user) {
		User obj = userService.create(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> Update(@RequestBody User user, @PathVariable Long id) {
		user.setId(id);
		userService.Update(user);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Deletar(@PathVariable Long id) {
		userService.Deletar(id);
		return ResponseEntity.noContent().build();

	}

}

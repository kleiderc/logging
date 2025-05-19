package com.project.structure.database.logging.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.structure.database.logging.model.User;
import com.project.structure.database.logging.service.UserService;

@RestController
@RequestMapping("/users") // Base URL
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {

		return ResponseEntity.ok(service.create(user));// triggers SQL logs
	}

	@GetMapping
	public ResponseEntity<List<User>> getAll() {

		return ResponseEntity.ok(service.getAllUsers());
	}

	@GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
		
    	final User user = service.findUserById(id);
    	
        return ResponseEntity.ok(user);
                
    }

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {

		return ResponseEntity.ok(service.update(id, user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/error")
	public void causeError() {

		service.fail(); // triggers exception
	}
}

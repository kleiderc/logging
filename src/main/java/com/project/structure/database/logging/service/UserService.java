/**
 * 
 */
package com.project.structure.database.logging.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.structure.database.logging.model.User;
import com.project.structure.database.logging.repository.UserRepository;

import jakarta.annotation.PostConstruct;

/**
 * Service layer (Business Logic layer) for user operations.
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		logger.info("✅ Service initialized");
		logger.debug("🐞 Service debug log");
		logger.error("🔥 Simulated error log");
	}

	public List<User> getAllUsers() {

		logger.info("Retrieve all users");

		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public User findUserById(final Long id) {

		logger.debug("Fetching user with ID: {}", id);
		System.out.println("kleider");
		final User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found with ID"));

		return user;
	}

	public User update(Long id, User userDetails) {

		logger.debug("Updating user with ID: {}", userDetails.getId());

		User user = userRepository.findById(id).orElseThrow();
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setAge(userDetails.getAge());

		return userRepository.save(user); // Update
	}

	public User create(final User user) {

		logger.debug("Creating user with ID: {}", user.getId());

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {

		logger.debug("Deleting user with ID: {}", id);

		userRepository.deleteById(id);
	}

	public User fail() {

		logger.error("About to throw an error"); // service.log
		throw new RuntimeException("Simulated failure"); // triggers error.log
	}
}
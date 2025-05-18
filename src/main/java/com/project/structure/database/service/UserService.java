/**
 * 
 */
package com.project.structure.database.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.structure.database.model.User;
import com.project.structure.database.repository.UserRepository;

/**
 * Service layer (Business Logic layer) for user operations.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {

		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public Optional<User> findUserById(final Long id) {

		Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElse(null));
		if (user == null) {
			throw new RuntimeException("User not found!");
		}

		return user;
	}

	public User update(Long id, User userDetails) {

		User user = userRepository.findById(id).orElseThrow();
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setAge(userDetails.getAge());

		return userRepository.save(user); // Update
	}

	public User create(final User user) {

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {

		userRepository.deleteById(id);
	}

}
/**
 * 
 */
package com.project.structure.database.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.structure.database.logging.model.User;

/**
 * Provides basic CRUD operations using JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
	// No need to implement anything, JpaRepository provides CRUD
}
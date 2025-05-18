/**
 * 
 */
package com.project.structure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.structure.database.model.User;

/**
 * Provides basic CRUD operations using JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
	// No need to implement anything, JpaRepository provides CRUD
}
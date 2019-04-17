package com.berzenin.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berzenin.university.model.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}

package com.sbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User>  findByEmail(String email);

	User findByName(String username);

}

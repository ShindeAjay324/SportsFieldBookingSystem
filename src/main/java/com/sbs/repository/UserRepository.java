package com.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}

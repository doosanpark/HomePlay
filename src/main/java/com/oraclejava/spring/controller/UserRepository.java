package com.oraclejava.spring.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<R_member, Integer> {

	Optional<R_member> findById(String id);

}

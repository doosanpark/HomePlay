package com.oraclejava.spring.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.member.R_member;

public interface UserRepository extends JpaRepository<R_member, Integer> {

	Optional<R_member> findById(String id);
	
	 @Query("select m from R_member m where m.id = :user_id") 
		List<R_member> findUser(String user_id);

}

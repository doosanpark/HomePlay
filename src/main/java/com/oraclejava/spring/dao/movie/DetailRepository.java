package com.oraclejava.spring.dao.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraclejava.spring.model.movie.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

}

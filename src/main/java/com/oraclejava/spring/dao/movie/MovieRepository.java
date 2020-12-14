package com.oraclejava.spring.dao.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraclejava.spring.bean.movie.R_movie;

public interface MovieRepository extends JpaRepository<R_movie, Integer> {

}

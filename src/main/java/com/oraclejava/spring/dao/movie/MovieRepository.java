package com.oraclejava.spring.dao.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraclejava.spring.model.movie.MovieBean;

public interface MovieRepository extends JpaRepository<MovieBean, Integer> {

}

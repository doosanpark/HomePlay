package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select m from R_movie m where m.genre like '%' || :genreName || '%' order by m.no") 
	List<Movie> findGenre(String genreName, Pageable pageable);
  
    @Query("select m from R_movie m where m.genre like '%' || :genreName || '%' order by m.title")
    Page<Movie> findGenreList(String genreName, Pageable pageable);
}

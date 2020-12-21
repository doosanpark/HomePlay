package com.oraclejava.spring.dao.drama;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.bean.movie.Drama;
import com.oraclejava.spring.bean.movie.Movie;

public interface DramaRepository extends JpaRepository<Drama, Integer>{
	 @Query("select m from R_movie m where m.genre like '%' || :genreName || '%' order by m.no") 
		List<Movie> findGenre(String genreName, Pageable pageable);
	  
     @Query("select m from R_movie m where m.genre like '%' || :genreName || '%' order by m.title")
	    Page<Movie> findGenreList(String genreName, Pageable pageable);
}

package com.oraclejava.spring.dao.drama;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.drama.Drama;

public interface DramaRepository extends JpaRepository<Drama, Integer>{
	 @Query("select m from R_drama m where m.genre like '%' || :genreName || '%' order by m.no") 
		List<Drama> findGenre(String genreName, Pageable pageable);
	  
     @Query("select m from R_drama m where m.genre like '%' || :genreName || '%' order by m.title")
	    Page<Drama> findGenreList(String genreName, Pageable pageable);
}

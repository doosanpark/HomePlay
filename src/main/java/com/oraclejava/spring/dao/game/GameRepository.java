package com.oraclejava.spring.dao.game;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	@Query("select g from R_game g where g.genre like '%' || :genreName || '%' order by g.no") 
	List<Game> findGenre(String genreName, Pageable pageable);
  
    @Query("select g from R_game g where g.genre like '%' || :genreName || '%' order by g.title")
    Page<Game> findGenreList(String genreName, Pageable pageable);
			
			
}

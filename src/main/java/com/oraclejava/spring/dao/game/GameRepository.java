package com.oraclejava.spring.dao.game;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	@Query("select m from R_game m where m.genre like '%' || :genreName || '%' order by m.no") 
	List<Game> findGenre(String genreName, Pageable pageable);
  
    @Query("select m from R_game m where m.genre like '%' || :genreName || '%' order by m.title")
    Page<Game> findGenreList(String genreName, Pageable pageable);
			
			
}

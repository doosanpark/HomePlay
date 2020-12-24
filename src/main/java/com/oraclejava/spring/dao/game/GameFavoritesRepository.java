package com.oraclejava.spring.dao.game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.game.GFavorites;

public interface GameFavoritesRepository extends JpaRepository<GFavorites, Integer>{

	 @Query("select v from v_fmg v where v.id = :user_id")
		List<GFavorites> findFavorite(String user_id);

}

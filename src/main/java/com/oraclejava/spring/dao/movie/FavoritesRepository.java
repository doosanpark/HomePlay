package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.oraclejava.spring.model.movie.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer>{

	 @Query("select v from v_fm v where v.id = :user_id")
		List<Favorites> findFavorite(String user_id);

}

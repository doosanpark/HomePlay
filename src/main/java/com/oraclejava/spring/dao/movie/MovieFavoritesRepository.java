package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.oraclejava.spring.model.movie.MFavorites;

public interface MovieFavoritesRepository extends JpaRepository<MFavorites, Integer>{

	 @Query("select v from v_fm v where v.id = :user_id")
		List<MFavorites> findFavorite(String user_id);

}

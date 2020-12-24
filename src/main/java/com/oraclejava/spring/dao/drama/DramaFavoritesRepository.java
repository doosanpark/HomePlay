package com.oraclejava.spring.dao.drama;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.drama.DFavorites;

public interface DramaFavoritesRepository extends JpaRepository<DFavorites, Integer>{

	 @Query("select v from v_fmd v where v.id = :user_id")
		List<DFavorites> findFavorite(String user_id);

}

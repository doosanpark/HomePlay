package com.oraclejava.spring.dao.game;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.game.GDetail;


public interface GameDetailRepository extends JpaRepository<GDetail, Integer> {
	
    @Query("select count(f.sn) from Favorite f where f.id =:id") 
	public int count_favorite(String id);
    
    
}

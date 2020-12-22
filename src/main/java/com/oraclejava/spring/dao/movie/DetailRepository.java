package com.oraclejava.spring.dao.movie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.movie.MDetail;

public interface DetailRepository extends JpaRepository<MDetail, Integer> {
	
    @Query("select count(f.sn) from Favorite f where f.id =:id") 
	public int count_favorite(String id);
    
    
}

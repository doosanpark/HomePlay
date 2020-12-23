package com.oraclejava.spring.dao.drama;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.drama.DDetail;

public interface DramaDetailRepository extends JpaRepository<DDetail, Integer> {
	
    @Query("select count(f.sn) from Favorite f where f.id =:id") 
	public int count_favorite(String id);
    
    
}

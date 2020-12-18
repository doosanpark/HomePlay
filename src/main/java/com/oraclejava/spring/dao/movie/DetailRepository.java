package com.oraclejava.spring.dao.movie;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.model.movie.Detail;
import com.oraclejava.spring.model.movie.RStaff;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
	
    @Query("select count(f.sn) from Favorite f where f.id =:id") 
	public int count_favorite(String id);
    
    
}

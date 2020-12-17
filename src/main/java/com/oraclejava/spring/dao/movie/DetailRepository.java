package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.model.movie.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    @Query("select id from R_member where no=:no ") 
	String findId(int no);
    
}

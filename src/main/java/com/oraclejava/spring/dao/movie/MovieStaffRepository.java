package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.movie.RStaff;

public interface MovieStaffRepository extends JpaRepository<RStaff, Integer> {

    @Query("select r from RStaff r where r.movie_no =:id") 
    List<RStaff> findStaff(int id);
    
}
package com.oraclejava.spring.dao.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oraclejava.spring.model.contents.RStaff;

public interface StaffRepository extends JpaRepository<RStaff, Integer> {

    @Query("select r from RStaff r where r.title =:title") 
    List<RStaff> findStaff(String title);
    
}

package com.oraclejava.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraclejava.spring.model.detail.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

}

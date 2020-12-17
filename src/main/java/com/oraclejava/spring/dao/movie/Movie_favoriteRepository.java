package com.oraclejava.spring.dao.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraclejava.spring.model.movie.Favorite;

public interface Movie_favoriteRepository extends JpaRepository<Favorite, Integer>{

}

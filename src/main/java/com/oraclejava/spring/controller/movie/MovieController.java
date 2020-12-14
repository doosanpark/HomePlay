package com.oraclejava.spring.controller.movie;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.R_movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class MovieController {

	  @Autowired
	   private MovieRepository movieRepository;
	   
	   private static final int PAGE_SIZE = 10; 
	   
	   @RequestMapping(value="movie/list",
	         method=RequestMethod.GET)
	   public ModelAndView index() {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("movie/list/movieList");
	      List<R_movie> lists = 
	            movieRepository.findAll();
	      mav.addObject("movieList", lists);
	      
	      return mav;
	   }

}

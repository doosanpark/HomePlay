package com.oraclejava.spring.controller.movie;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	            movieRepository.findGenre("로맨스", PageRequest.of(0, 4));
	      List<R_movie> lists2 = 
		            movieRepository.findGenre("액션", PageRequest.of(0, 4));
	      List<R_movie> lists3 = 
	    		  movieRepository.findGenre("애니메이션", PageRequest.of(0, 4));
	      List<R_movie> lists4 = 
	    		  movieRepository.findGenre("코미디", PageRequest.of(0, 4));
	      mav.addObject("movieListRomance", lists);
	      mav.addObject("movieListAction", lists2);
	      mav.addObject("movieListAni", lists3);
	      mav.addObject("movieListComi", lists4);
	      
	      return mav;
	   }

}

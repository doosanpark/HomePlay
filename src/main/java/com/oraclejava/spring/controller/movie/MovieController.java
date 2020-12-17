package com.oraclejava.spring.controller.movie;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class MovieController {

	  @Autowired
	   private MovieRepository movieRepository;
	   
	   private static final int PAGE_SIZE = 6; 
	   
	   @RequestMapping(value="movie/list",
	         method=RequestMethod.GET)
	   public ModelAndView index() {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("movie/list/movieList");
	      return mav;
	   }
	   
	   @RequestMapping(path="/movie/list/{genre}/{pageNumber}", method = RequestMethod.GET)
	   public String genreList(@PathVariable String genre, Integer pageNumber, Model model) {
		  
		   pageNumber = (pageNumber == null) ? 1 : pageNumber;
		   
		   return "movie/list/movieListDetail";
	   }
	   
}

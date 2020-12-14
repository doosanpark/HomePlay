package com.oraclejava.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.R_movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class HomeController {

	
	  @Autowired
	   private MovieRepository movieRepository;
	
	
	   @RequestMapping(value="body/center",
		         method=RequestMethod.GET)
		   public ModelAndView body_center() {
		      ModelAndView rec = new ModelAndView();
		      rec.setViewName("includes/body_center");
		      List<R_movie> lists = 
		            movieRepository.findAll();
		      rec.addObject("movieRecommend", lists);
		      
		      return rec;
		   }

}

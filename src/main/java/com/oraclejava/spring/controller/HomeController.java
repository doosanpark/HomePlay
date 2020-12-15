package com.oraclejava.spring.controller;

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
public class HomeController {

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView body_center() {
		ModelAndView rec = new ModelAndView();
		rec.setViewName("home/main");
		List<R_movie> lists = movieRepository.findAll();
		rec.addObject("movieRecommend", lists);

		
		List<R_movie> movieLists = movieRepository.findGenre("로맨스", PageRequest.of(0, 4));
		List<R_movie> movieLists2  = movieRepository.findGenre("액션", PageRequest.of(0, 4));
		List<R_movie> movieLists3  = movieRepository.findGenre("애니메이션", PageRequest.of(0, 4));
		List<R_movie> movieLists4  = movieRepository.findGenre("코미디", PageRequest.of(0, 4));
		rec.addObject("movieListRomance", lists);
		rec.addObject("movieListAction", movieLists2);
		rec.addObject("movieListAni", movieLists3);
		rec.addObject("movieListComi", movieLists4);
		
		return rec;
	}

}

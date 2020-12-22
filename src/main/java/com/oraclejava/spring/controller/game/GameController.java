package com.oraclejava.spring.controller.game;

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

import com.oraclejava.spring.dao.game.GameRepository;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.model.game.Game;

@Controller
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	private static final int PAGE_SIZE = 5;
	
	@RequestMapping(value="game/list",
			method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("game/list/list");
		List<Game> lists = 
				gameRepository.findGenre("RPG", PageRequest.of(0, 6));
		List<Game> lists2 = 
				gameRepository.findGenre("MOBA", PageRequest.of(0, 6));
		List<Game> lists3 = 
				gameRepository.findGenre("SPORTS", PageRequest.of(0, 6));
		List<Game> lists4 = 
				gameRepository.findGenre("Console", PageRequest.of(0, 6));
		mav.addObject("gameListRPG",lists);		
		mav.addObject("gameListMOBA",lists2);		
		mav.addObject("gameListSPORTS",lists3);		
		mav.addObject("gameListConsole",lists4);
		
		
		return mav;
		
		
	}
	
	@RequestMapping(path="/game/list/{genre}/{pageNumber}", method = RequestMethod.GET)
	public String genreList(@PathVariable String genre, @PathVariable Integer pageNumber, Model model) {
		
		
		pageNumber = (pageNumber == null) ? 1 : pageNumber;
		Page<Game> games = gameRepository.findGenreList(genre, PageRequest.of(pageNumber -1, PAGE_SIZE, Sort.by("title")));
		int begin = 1;
		int end = games.getTotalPages();
		int current = games.getNumber() + 1;
		model.addAttribute("gameList", games.getContent());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("genreName", genre);
		
		
		return "game/list/listDetail";
		
	}
	
}

package com.oraclejava.spring.controller.drama;

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

import com.oraclejava.spring.dao.drama.DramaRepository;
import com.oraclejava.spring.model.drama.Drama;
import com.oraclejava.spring.model.movie.Movie;

@Controller
public class DramaController {

	@Autowired
	private DramaRepository dramaRepository;
	
	private static final int PAGE_SIZE = 6;
	
	@RequestMapping(value="drama/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contents/drama/list/list");
		List<Drama> list1 = dramaRepository.findGenre("범죄", PageRequest.of(0, 6));
		List<Drama> list2 = dramaRepository.findGenre("미스터리", PageRequest.of(0, 6));
		List<Drama> list3 = dramaRepository.findGenre("코미디", PageRequest.of(0, 6));
		
		mav.addObject("dramaListCri", list1);
		mav.addObject("dramaListMis", list2);
		mav.addObject("dramaListComi", list3);	
		
		return mav;
	}
	
	@RequestMapping(path="/drama/list/{genre}/{pageNumber}", method = RequestMethod.GET)
    public String genreList(@PathVariable String genre,@PathVariable Integer pageNumber, Model model) {
       
       pageNumber = (pageNumber == null) ? 1 : pageNumber;
       Page<Drama> dramas = dramaRepository.findGenreList(genre, PageRequest.of(pageNumber -1, PAGE_SIZE, Sort.by("title")));         
       int begin = 1;
       int end = dramas.getTotalPages();
       int current = dramas.getNumber() + 1;
       model.addAttribute("dramaList", dramas.getContent());
       model.addAttribute("beginIndex", begin);
       model.addAttribute("endIndex", end);
       model.addAttribute("currentIndex", current);
       model.addAttribute("genreName", genre);
       return "contents/drama/list/listDetail";
    }
	
}

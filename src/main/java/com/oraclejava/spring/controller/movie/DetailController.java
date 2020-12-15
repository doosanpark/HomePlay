package com.oraclejava.spring.controller.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.dao.movie.DetailRepository;

@Controller
public class DetailController {

	
	
	
	@Autowired
	private DetailRepository detailRepository;
	
	/*
	 * @RequestMapping(value="/detail/detail", method=RequestMethod.GET) public
	 * String indexTop() { return "redirect:/detail/detail/1"; }
	 */
	
	@RequestMapping(value="/detail/detail/{NO}",
					method=RequestMethod.GET)
	public ModelAndView index(@PathVariable Integer NO) {
	ModelAndView mav = new ModelAndView();
	mav.setViewName("detail/detail");
	mav.addObject("detail", detailRepository.findById(NO).get());
	
	return mav;
	}
}

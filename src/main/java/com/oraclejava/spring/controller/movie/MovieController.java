package com.oraclejava.spring.controller.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {

	@Autowired
	private MovieController movieConttroller;
	
	private static final int PAGE_SIZE = 10; 
	
	public ModelAndView ssasd() {
		return null;
	}
}

	

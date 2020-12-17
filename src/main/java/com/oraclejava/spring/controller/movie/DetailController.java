package com.oraclejava.spring.controller.movie;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.dao.movie.DetailRepository;
import com.oraclejava.spring.model.movie.Detail;
import com.oraclejava.spring.model.movie.r_review;

@Controller
public class DetailController {

	
	@Autowired
	private DetailRepository detailRepository;
	
	/*
	 * @RequestMapping(value="/detail/detail", method=RequestMethod.GET) public
	 * String indexTop() { return "redirect:/detail/detail/1"; }
	 */
	
	@RequestMapping(value="/detail/{NO}", method=RequestMethod.GET)
	public ModelAndView index( @PathVariable Integer NO) {
	ModelAndView mav = new ModelAndView();
	
	mav.setViewName("detail/detail");
	mav.addObject("detail", detailRepository.findById(NO).get());
	
	return mav;
	}
	
	@RequestMapping(path = "/detail/{NO}", method = RequestMethod.POST)
	public String review(@ModelAttribute r_review r_review, BindingResult bindingResult, Model model) {
		int movie_no=r_review.getDetail().getNo();
		String review =r_review.getContent();
		int grade=r_review.getGrade();
		String title= r_review.getTitle();
		Detail detail=detailRepository.findById(movie_no).get();
		
		Date time = new Date();
		  
		 detail.getReview().add(new r_review(5,title,review,grade ,time,detail));
		
		detailRepository.save(detail);
		
		return "redirect:/detail/"+movie_no;
	}
	
	
}

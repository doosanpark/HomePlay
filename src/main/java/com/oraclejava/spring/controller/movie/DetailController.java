package com.oraclejava.spring.controller.movie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.oraclejava.spring.model.movie.RReview;

@Controller
public class DetailController {

	@Autowired
	private DetailRepository detailRepository;

	/*
	 * @RequestMapping(value="/detail/detail", method=RequestMethod.GET) public
	 * String indexTop() { return "redirect:/detail/detail/1"; }
	 */

	@RequestMapping(value = "/detail/{NO}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable Integer NO) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("movie/detail/detail");
		mav.addObject("detail", detailRepository.findById(NO).get());
		
		List<RReview> rev = detailRepository.findById(NO).get().getReview();
        int id=rev.get(1).getMember_no();
        String m_id=detailRepository.findId(id);
        
        

		return mav;
	}

	@RequestMapping(path = "/detail/{NO}", method = RequestMethod.POST)
	public String review(@ModelAttribute RReview r_review, BindingResult bindingResult, Model model) {
		int movie_no = r_review.getDetail().getNo();
		String review = r_review.getContent();
		int grade = r_review.getGrade();
		String title = r_review.getTitle();
		Detail detail = detailRepository.findById(movie_no).get();
		
		Date time = new Date();

		detail.getReview().add(new RReview(5, title, review, grade, time, detail));

		detailRepository.save(detail);

		return "redirect:/detail/" + movie_no;
	}

}

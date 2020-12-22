package com.oraclejava.spring.controller.movie;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.dao.movie.DetailRepository;
import com.oraclejava.spring.dao.movie.MovieStaffRepository;
import com.oraclejava.spring.dao.movie.Movie_favoriteRepository;
import com.oraclejava.spring.model.movie.Detail;
import com.oraclejava.spring.model.movie.Favorite;
import com.oraclejava.spring.model.movie.RReview;
import com.oraclejava.spring.model.movie.RStaff;

@Controller
public class DetailController {

	@Autowired
	private DetailRepository detailRepository;
	@Autowired
	private Movie_favoriteRepository favoriteRepository;
	@Autowired
	private MovieStaffRepository staffRepository;
	/*
	 * @RequestMapping(value="/detail/detail", method=RequestMethod.GET) public
	 * String indexTop() { return "redirect:/detail/detail/1"; }
	 */

	@RequestMapping(value = "/detail/{NO}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable Integer NO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		RStaff staff = staffRepository.findById(NO).get();
		int movie_no = staff.getMovie_no();
		
		
		mav.setViewName("movie/detail/detail");
		mav.addObject("detail", detailRepository.findById(NO).get());
		mav.addObject("staff", staffRepository.findById(movie_no).get());
		mav.addObject("favoriteCount", detailRepository.count_favorite(s_id));

		return mav;
	}

	@RequestMapping(path = "/detail/favorite", method = RequestMethod.POST)
	public String favorite(@ModelAttribute Favorite favorite, @RequestParam Integer count, BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
        
		favorite.setId(s_id);

		if (count == 0) {

			
			Detail detail = detailRepository.findById(favorite.getDetail().getNo()).get();
			detail.getFavorites().add(favorite);
			detailRepository.save(detail);
		}
		
		

		return "redirect:/detail/" + favorite.getDetail().getNo();

	}

	@RequestMapping(path = "/detail/{NO}", method = RequestMethod.POST)
	public String review(@ModelAttribute RReview r_review, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		int movie_no = r_review.getDetail().getNo();
		String review = r_review.getContent();
		int grade = r_review.getGrade();
		String title = r_review.getTitle();
		Detail detail = detailRepository.findById(movie_no).get();

		Date time = new Date();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		int s_no = (int) session.getAttribute("user_no");

		detail.getReview().add(new RReview(s_no, title, review, grade, time, detail, s_id));

		detailRepository.save(detail);

		return "redirect:/detail/" + movie_no;
	}

}

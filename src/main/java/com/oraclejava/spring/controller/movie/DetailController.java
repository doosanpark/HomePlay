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

import com.oraclejava.spring.dao.drama.DramaDetailRepository;
import com.oraclejava.spring.dao.game.GameDetailRepository;
import com.oraclejava.spring.dao.movie.MovieDetailRepository;
import com.oraclejava.spring.dao.movie.Movie_favoriteRepository;
import com.oraclejava.spring.dao.movie.StaffRepository;
import com.oraclejava.spring.model.drama.DDetail;
import com.oraclejava.spring.model.drama.DReview;
import com.oraclejava.spring.model.game.GDetail;
import com.oraclejava.spring.model.game.GReview;
import com.oraclejava.spring.model.movie.Favorite;
import com.oraclejava.spring.model.movie.MDetail;
import com.oraclejava.spring.model.movie.MReview;

@Controller
public class DetailController {

	@Autowired
	private MovieDetailRepository movieDetailRepository;
	@Autowired
	private DramaDetailRepository dramaDetailRepository;
	@Autowired
	private GameDetailRepository gameDetailRepository;
	@Autowired
	private Movie_favoriteRepository favoriteRepository;
	@Autowired
	private StaffRepository staffRepository;
	/*
	 * @RequestMapping(value="/detail/detail", method=RequestMethod.GET) public
	 * String indexTop() { return "redirect:/detail/detail/1"; }
	 */

	@RequestMapping(value = "/movie/detail/{NO}", method = RequestMethod.GET)
	public ModelAndView movieDetail(@PathVariable Integer NO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		/*
		 * RStaff staff = staffRepository.findById(NO).get(); int movie_no =
		 * staff.getMovie_no();
		 */
//		String title = movieDetailRepository.findById(NO).get().getTitle();
//		List<RStaff> staffs = staffRepository.findStaff(title);

		mav.setViewName("movie/detail/detail");
		mav.addObject("detail", movieDetailRepository.findById(NO).get());
		
		System.out.println("썸네일 : " + movieDetailRepository.findById(NO).get().getPoster());
		
//		mav.addObject("staff", staffs);
		mav.addObject("favoriteCount", movieDetailRepository.count_favorite(s_id));

		return mav;
	}

	@RequestMapping(path = "/movie/detail/favorite", method = RequestMethod.POST)
	public String movieFavorite(@ModelAttribute Favorite favorite, @RequestParam Integer count,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		favorite.setId(s_id);

		if (count == 0) {

			MDetail mdetail = movieDetailRepository.findById(favorite.getDetail().getNo()).get();
			mdetail.getFavorites().add(favorite);
			movieDetailRepository.save(mdetail);
		}

		return "redirect:/movie/detail/" + favorite.getDetail().getNo();

	}

	@RequestMapping(path = "/movie/detail/{NO}", method = RequestMethod.POST)
	public String movieReview(@ModelAttribute MReview r_review, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		int movie_no = r_review.getDetail().getNo();
		String review = r_review.getContent();
		int grade = r_review.getGrade();
		String title = r_review.getTitle();
		MDetail mdetail = movieDetailRepository.findById(movie_no).get();

		Date time = new Date();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		int s_no = (int) session.getAttribute("user_no");

		mdetail.getReview().add(new MReview(s_no, title, review, grade, time, mdetail, s_id));

		movieDetailRepository.save(mdetail);

		return "redirect:/movie/detail/" + movie_no;
	}

	@RequestMapping(value = "/drama/detail/{NO}", method = RequestMethod.GET)
	public ModelAndView dramaDetail(@PathVariable Integer NO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		System.out.println("s_id" + s_id);

//		String title = dramaDetailRepository.findById(NO).get().getTitle();
//		List<RStaff> staffs = staffRepository.findStaff(title);

		mav.setViewName("drama/detail/detail");
		mav.addObject("detail", dramaDetailRepository.findById(NO).get());
//		mav.addObject("staff", staffs);
		mav.addObject("favoriteCount", dramaDetailRepository.count_favorite(s_id));

		return mav;
	}

	@RequestMapping(path = "/drama/detail/favorite", method = RequestMethod.POST)
	public String dramaFavorite(@ModelAttribute Favorite favorite, @RequestParam Integer count,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		favorite.setId(s_id);

		if (count == 0) {

			DDetail ddetail = dramaDetailRepository.findById(favorite.getDetail().getNo()).get();
			ddetail.getFavorites().add(favorite);
			dramaDetailRepository.save(ddetail);
		}

		return "redirect:/drama/detail/" + favorite.getDetail().getNo();

	}

	@RequestMapping(path = "/drama/detail/{NO}", method = RequestMethod.POST)
	public String dramaReview(@ModelAttribute MReview r_review, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		int drama_no = r_review.getDetail().getNo();
		String review = r_review.getContent();
		int grade = r_review.getGrade();
		String title = r_review.getTitle();
		DDetail ddetail = dramaDetailRepository.findById(drama_no).get();

		Date time = new Date();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		int s_no = (int) session.getAttribute("user_no");

		ddetail.getReview().add(new DReview(s_no, title, review, grade, time, ddetail, s_id));

		dramaDetailRepository.save(ddetail);

		return "redirect:/drama/detail/" + drama_no;
	}

	@RequestMapping(value = "/game/detail/{NO}", method = RequestMethod.GET)
	public ModelAndView gameDetail(@PathVariable Integer NO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		System.out.println("s_id" + s_id);

//		String title = gameDetailRepository.findById(NO).get().getTitle();
//		List<RStaff> staffList = staffRepository.findStaff(title);

		mav.setViewName("game/detail/detail");
		mav.addObject("detail", gameDetailRepository.findById(NO).get());
		// mav.addObject("staff", staffList);
		mav.addObject("favoriteCount", gameDetailRepository.count_favorite(s_id));

		return mav;
	}

	@RequestMapping(path = "/game/detail/favorite", method = RequestMethod.POST)
	public String gameFavorite(@ModelAttribute Favorite favorite, @RequestParam Integer count,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		favorite.setId(s_id);

		if (count == 0) {

			GDetail gdetail = gameDetailRepository.findById(favorite.getDetail().getNo()).get();
			gdetail.getFavorites().add(favorite);
			gameDetailRepository.save(gdetail);
		}

		return "redirect:/game/detail/" + favorite.getDetail().getNo();

	}

	@RequestMapping(path = "/game/detail/{NO}", method = RequestMethod.POST)
	public String gameReview(@ModelAttribute MReview r_review, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		int game_no = r_review.getDetail().getNo();
		String review = r_review.getContent();
		int grade = r_review.getGrade();
		String title = r_review.getTitle();
		GDetail gdetail = gameDetailRepository.findById(game_no).get();

		Date time = new Date();

		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		int s_no = (int) session.getAttribute("user_no");

		gdetail.getReview().add(new GReview(s_no, title, review, grade, time, gdetail, s_id));

		gameDetailRepository.save(gdetail);

		return "redirect:/game/detail/" + game_no;
	}

}

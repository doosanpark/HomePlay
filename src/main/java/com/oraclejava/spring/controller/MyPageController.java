package com.oraclejava.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oraclejava.spring.dao.movie.FavoritesRepository;


@Controller
public class MyPageController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FavoritesRepository favoritesRepository;

	
//	@RequestMapping(value = { "account/mypage" }, method = RequestMethod.GET)
//	public ModelAndView mypage(@ModelAttribute R_member form, HttpServletRequest request, HttpServletResponse response) {
//	
//
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("account/mypage");	
//		
//	HttpSession session = request.getSession();
//	String id = (String)session.getAttribute("user_id");
//
//	List<R_member> user_info = userRepository.findUser(id);
//	mav.addObject("user_info", user_info);
//	
//	List<Favorites> findFavorite = favoritesRepository.findFavorite(id);
//	mav.addObject("findFavorite", findFavorite);
//
//
//	return mav;
//	}

	
}

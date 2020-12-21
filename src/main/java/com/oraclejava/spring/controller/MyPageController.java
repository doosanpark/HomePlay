package com.oraclejava.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.FavoritesRepository;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.model.member.R_member;
import com.oraclejava.spring.model.movie.Favorites;


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

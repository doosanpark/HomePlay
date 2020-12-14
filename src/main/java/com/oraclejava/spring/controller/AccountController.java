package com.oraclejava.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.model.R_member;

@Controller
public class AccountController {

	@Autowired
	private UserRepository userRepository;


	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("msg", "안녕 여러분");

		return mav;
	}
	
	/* 로그인 화면 구현 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("/login...(ok)");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/account/login_basic");
		mav.addObject("msg", "(login_basic.html)");
		return mav;
	}
	
	/* 로그인 로직 구현 */
	@RequestMapping(value = { "/login/id_ck" }, method = RequestMethod.POST)
	public ModelAndView id_ck(@ModelAttribute R_member form) {
		System.out.println("/login/id_ck...(ok)");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/account/login_ck_result");

		try {
			R_member r_member = userRepository.findById(form.getId()).get();
			mav.addObject("msg1", r_member.getId());
			mav.addObject("msg2", r_member.getPass());
			mav.addObject("msg3", r_member.getEmail());
			return mav;

		} catch (Exception e) {
			mav.addObject("msg1", "찾을 수 없는 ID입니다.");
			mav.addObject("msg2", e);
			return mav;
		}
	}
	
	

}

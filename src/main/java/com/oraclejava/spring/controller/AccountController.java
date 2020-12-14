package com.oraclejava.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		mav.setViewName("home/main");

		return mav;
	}
	
	/* 로그인 화면 구현 */
	@RequestMapping(value = { "account/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("/login...(ok)");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/login");
		mav.addObject("msg", "(login.html)");
		return mav;
	}
	
	/* 로그인 로직 구현 */
	@RequestMapping(value = { "account/loginsuccess" }, method = RequestMethod.POST)
	public ModelAndView id_ck(@ModelAttribute R_member form) {
		System.out.println("/login/loginsuccess...(ok) : " + form.getId());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/loginsuccess");

		try {
			R_member r_member = userRepository.findById(form.getId()).get();
			
			String DB_Pass = r_member.getPass();
			
			if (form.getPass().equals(DB_Pass)) {
				
				mav.addObject("msg1", r_member.getId());
				mav.addObject("msg2", r_member.getPass());
				mav.addObject("msg3", r_member.getEmail());
			}else {
				mav.addObject("msg1", r_member.getId()+"님 ");
				mav.addObject("msg2", "비밀번호가 틀립니다.ㅜㅠ");
				mav.addObject("msg3", "다시 입력해 주세요");
				
			}
			
			return mav;

		} catch (Exception e) {
			mav.addObject("msg1", "아이디를 찾을 수 없습니다.");
			mav.addObject("msg2", "다시 입력해 주세요");
			mav.addObject("msg3", "Message:"+e);
			return mav;
		}
	}
	
	/* 회원가입 화면 구현 */
	@RequestMapping(params="join",value = { "account/loginsuccess" }, method = RequestMethod.POST)
	public ModelAndView join() {
		System.out.println("/join...(ok)");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/join");
		return mav;
	}
	
	/* 회원가입 로직 구현 */
	@RequestMapping(value = {"account/join"}, method = RequestMethod.POST)
	public String join_ck(@ModelAttribute MemberForm form,
								 BindingResult bindingResult,
								 Model model) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/account/join";
		}
		
		R_member member = userRepository.findById(form.getId()).get();
		member.setId(form.getId());
		member.setPass(form.getPass());
		member.setEmail(form.getEmail());
		
		/* ID 중복 체크 */
		
		
		userRepository.save(member);
		
		return "redirect:/home/main";
	}
}

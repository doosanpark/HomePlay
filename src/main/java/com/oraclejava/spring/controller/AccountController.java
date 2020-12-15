package com.oraclejava.spring.controller;

import java.util.Date;

import javax.persistence.SequenceGenerator;
import javax.persistence.SequenceGenerators;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.model.R_member;

@Controller
public class AccountController {

	@Autowired
	private UserRepository userRepository;
	private HttpServletRequest HttpServletRequest;
	private HttpServletResponse HttpServletResponse;

	
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
	public ModelAndView id_ck(@ModelAttribute R_member form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("/login/loginsuccess...(ok) : " + form.getId());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/loginsuccess");

		try {
			R_member r_member = userRepository.findById(form.getId()).get();
			
			String DB_Pass = r_member.getPass();
			
			if (form.getPass().equals(DB_Pass)) {
				
				/* Session 등록 */
				
				HttpSession session = request.getSession();
				request.setCharacterEncoding("utf-8");
				session.setAttribute("user_id", r_member.getId());
				
				mav.addObject("msg1", r_member.getId());
				mav.addObject("msg2", r_member.getPass());
				mav.addObject("msg3", r_member.getEmail());
				mav.addObject("msg4", session.getAttribute("user_id"));
				
				System.out.println("Session...(ok) : " + form.getId());
				
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
	@RequestMapping(value = { "account/sc_join" }, method = RequestMethod.GET)
	public ModelAndView join() {
		System.out.println("/join...(ok)");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/join");
		return mav;
	}
	
	/* 회원가입 로직 구현 */
	@RequestMapping(params="db_join", value = { "account/sc_join" }, method = RequestMethod.POST)
	public String join(@ModelAttribute R_member form, Model model) {
		System.out.println("/join params=db_join...(ok)");
		
		R_member rmember = new R_member();
		Date today = new Date();
		
		
			
		/* 'no'field는 Sequence 자동 작동 */
		//rmember.setNo();
		rmember.setId(form.getId());
		rmember.setPass(form.getPass());
		rmember.setEmail(form.getEmail());
		rmember.setReg_date(today);
		
		System.out.println(form.getId());
		System.out.println(form.getPass());
		System.out.println(form.getEmail());
		System.out.println(today);
		
		userRepository.save(rmember);
		
		return "redirect:/";
	}
}

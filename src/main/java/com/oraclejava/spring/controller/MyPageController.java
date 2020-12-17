package com.oraclejava.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.model.R_member;

@Controller
public class MyPageController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = { "account/mypage" }, method = RequestMethod.GET)
	public ModelAndView mypage(@ModelAttribute R_member form, HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/mypage");	
		
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("user_id");
	long accessTime = (long)session.getCreationTime();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	String createTimeString = dateFormat.format(new Date(accessTime));

	System.out.println(id);
	System.out.println(accessTime);
	
	
	List<R_member> user_info = userRepository.findUser(id);
	mav.addObject("user_info", user_info);
	mav.addObject("accessTime", createTimeString);
	return mav;
	}
	
	

}

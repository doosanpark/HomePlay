package com.oraclejava.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String join(@ModelAttribute MemberForm form, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("/join params=db_join...(ok)");
		
		R_member rmember = new R_member();
		
		String id = form.getId();
		String pass = form.getPass();
		String pass2 = form.getPass2();
		Date today = new Date();

		String idReg = "^[a-z]+[a-z0-9]{5,19}$";
		String passReg = "^(?=.*\\d)(?=.*[a-z]).{8,}$"; // 영문 숫자를 포함한 8글자 이상. 특문 제외 (?=.*[~`!@#$%\\^&*()-])

		
		/* 'no'field는 Sequence 자동 작동 */
		//rmember.setNo();
		
		rmember.setPass(form.getPass());
		rmember.setEmail(form.getEmail());
		rmember.setReg_date(today);
		
		/* ID CHECK */
		if (id.isEmpty()) { // NULL CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID가 공란입니다.');</script>");
			out.flush();

		} else if (id.equals(rmember.getId())) { // Duplicate CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 존재하는 ID입니다. 다른 아이디를 사용해 주세요.');</script>");
			out.flush();
			
		} else if (!id.matches(idReg)) { // 정규식 CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자여야 합니다.');</script>");
			out.flush();
		
		} else {
			rmember.setId(form.getId());
		}
		
		/* PASSWORD CHECK */
		if (pass.isEmpty()) { // NULL CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 공란입니다.');</script>");
			out.flush();
			
		} else if (!pass.matches(passReg)) { // 정규식 CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호는 영문자, 숫자를 포함하는 8글자 이상이어야 합니다.');</script>");
			out.flush();
		
		} else {
			rmember.setPass(form.getPass());
		}
		
		/* PASSWORD 2 CHECK */
		if (pass.isEmpty()) { // NULL CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 한번 더 입력해주세요.');</script>");
			out.flush();
			
		} else if (!pass2.equals(pass)) { // pass1 = pass2?
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 확인과 비밀번호의 값이 다릅니다.');</script>");
			out.flush();
		
		} else {
			rmember.setPass(form.getPass());
		}
		
			
		System.out.println(form.getId());
		System.out.println(form.getPass());
		System.out.println(form.getEmail());
		System.out.println(today);
		
		userRepository.save(rmember);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('축하합니다! 회원가입에 성공하셨습니다.'); </script>");
		out.flush();
		
		return "redirect:/";
	}
}

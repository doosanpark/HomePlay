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
	@RequestMapping(value="account/login", method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("/login...(try)");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/login");
		mav.addObject("msg", "(login.html)");
		return mav;
	}
	
	/* 로그인 로직 구현 */
	@RequestMapping(value="account/loginsuccess", method = RequestMethod.POST)
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
	@RequestMapping(value="account/sc_join", method = RequestMethod.GET)
	public ModelAndView join() {
		System.out.println("/join...(ok)");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/join");
		return mav;
	}
	
	/* 회원가입 로직 구현 */
	@RequestMapping(params="db_join", value="account/sc_join", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberForm form, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("/join params=db_join...(ok)");
		
		R_member rmember = new R_member();
		
		String id = form.getId();
		String pass = form.getPass();
		String pass2 = form.getPass2();
		Date today = new Date();
		
		R_member r_member = userRepository.findById(id).get();
		String DB_id = r_member.getId();
		
		String idReg = "^[a-z]+[a-z0-9]{5,19}$";
		String passReg = "^(?=.*\\d)(?=.*[a-z]).{8,}$"; // 영문 숫자를 포함한 8글자 이상. 특문 제외 (?=.*[~`!@#$%\\^&*()-])

		
		/* ID CHECK */
		if (id.isEmpty()) { // NULL CHECK
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID가 공란입니다.');</script>");
			out.flush();

		} else if (id.equals(DB_id)) { // Duplicate CHECK
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
		
		/* 'no'field는 Sequence 자동 작동 */
		rmember.setEmail(form.getEmail());
		rmember.setReg_date(today);
		
		userRepository.save(rmember);

		
		System.out.println(form.getId());
		System.out.println(form.getPass());
		System.out.println(form.getEmail());
		System.out.println(today);

		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('축하합니다! 회원가입에 성공하셨습니다.'); </script>");
		out.flush();
		
		return "redirect:/";
	}
	
	/* 정보 수정 화면 구현 */
	@RequestMapping(params="sc_modify", value="account/modify", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute MemberForm form, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[CHECK] modify...(ok)");
		
		/* 현재 Session ID값을 구한다.*/
		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		System.out.println("[CHECK] Session : "+s_id);

		/* Session ID에 해당하는 R_MEMBER 정보를 구한다.*/
		R_member r_member1 = userRepository.findById(s_id).get();
		ModelAndView mav1 = new ModelAndView();
		
		mav1.setViewName("account/modify"); 
		mav1.addObject("no", r_member1.getNo());
		mav1.addObject("id", r_member1.getId());
		mav1.addObject("pass", r_member1.getPass()); 
		mav1.addObject("email", r_member1.getEmail());
		System.out.println("[CHECK] no : "+r_member1.getNo());
		System.out.println("[CHECK] id : "+r_member1.getId());
		System.out.println("[CHECK] pass : "+r_member1.getPass());
		System.out.println("[CHECK] email : "+r_member1.getEmail());
		 
		return mav1;
	}
	
	/* 정보 수정 로직 구현 */
	@RequestMapping(params="db_modify", value="account/modify", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute R_member form, Model model, HttpServletRequest request) {
		System.out.println("[Check] r_member update Start...(ok)");
		System.out.println("[Check] r_member update Target PK No...("+form.getNo()+")");
		
		/* 업데이트할 대상 레코드를 검색한다. */
		R_member user = userRepository.findById(form.getNo()).get();
		
		/* DB update를 위한 레코드검색, 셋팅값 적용, 저장 실행*/
		//user.setNo(form.getNo());
		//user.setId(form.getId());
		user.setPass(form.getPass());
		user.setEmail(form.getEmail());
		user.setReg_date(new Date());

		userRepository.save(user);
		
		/* 현재 Session ID값을 구한다.*/
		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");


		System.out.println("[Check] r_member update Compleat...(ok)");
		
		/* 수정된 내용을 loginsuccess로 반환해서 확인할 수 있도록 한다. */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/loginsuccess");
		mav.addObject("msg1", form.getId());
		mav.addObject("msg2", form.getPass());
		mav.addObject("msg3", form.getEmail());
		mav.addObject("msg4", s_id);
		
		return mav;
		
	}
	
	/* 정보 수정 로직 구현 */
	@RequestMapping(params="del_member", value="account/modify", method = RequestMethod.GET)
	public ModelAndView modify(Model model,	HttpServletRequest request) {
		return null;
		
	}

}

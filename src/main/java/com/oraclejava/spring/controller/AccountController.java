package com.oraclejava.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oraclejava.spring.dao.drama.DramaFavoritesRepository;
import com.oraclejava.spring.dao.game.GameFavoritesRepository;
import com.oraclejava.spring.dao.movie.MovieFavoritesRepository;
import com.oraclejava.spring.model.drama.DFavorites;
import com.oraclejava.spring.model.game.GFavorites;
import com.oraclejava.spring.model.member.R_member;
import com.oraclejava.spring.model.movie.MFavorites;

@Controller
public class AccountController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpServletRequest HttpServletRequest;
	@Autowired
	private HttpServletResponse HttpServletResponse;
	@Autowired
	private MovieFavoritesRepository mFavoritesRepository;
	@Autowired
	private GameFavoritesRepository gFavoritesRepository;
	@Autowired
	private DramaFavoritesRepository dFavoritesRepository;

	/* 로그인 화면 구현 */
	@RequestMapping(value = "account/login", method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("/login...(try)");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/login");
		mav.addObject("msg", "(login.html)");
		return mav;
	}

	@RequestMapping(value = { "account/mypage" }, method = RequestMethod.GET)
	public ModelAndView mypage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/mypage");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("user_id");
		String pass = (String) session.getAttribute("user_pass");
		String email = (String) session.getAttribute("user_email");

		List<R_member> user_info = userRepository.findUser(id);
		mav.addObject("user_info", user_info);

		response.setContentType("text/html; charset=UTF-8");
		if(id==null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('계정 정보가 없습니다.'); history.go(-1);</script>");
			out.flush();
		}
		
		mav.addObject("msg1", id);
		mav.addObject("msg2", pass);
		mav.addObject("msg3", email);
		List<MFavorites> mFindFavorite = mFavoritesRepository.findFavorite(id);
		List<DFavorites> dFindFavorite = dFavoritesRepository.findFavorite(id);
		List<GFavorites> gFindFavorite = gFavoritesRepository.findFavorite(id);
		mav.addObject("mFindFavorite", mFindFavorite);
		mav.addObject("dFindFavorite", dFindFavorite);
		mav.addObject("gFindFavorite", gFindFavorite);
		return mav;
	}

	/* 로그인 로직 구현 */
	@RequestMapping(value = "account/mypage", method = RequestMethod.POST)
	public ModelAndView id_ck(@ModelAttribute R_member form, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/mypage");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			R_member r_member = userRepository.findById(form.getId()).get();

			String DB_Pass = r_member.getPass();

			if (form.getPass().equals(DB_Pass)) {

				/* Session 등록 */

				HttpSession session = request.getSession();
				request.setCharacterEncoding("utf-8");
				session.setAttribute("user_id", r_member.getId());
				session.setAttribute("user_pass", r_member.getPass());
				session.setAttribute("user_email", r_member.getEmail());
				session.setAttribute("user_no", r_member.getNo());

				mav.addObject("msg1", r_member.getId());
				mav.addObject("msg2", r_member.getPass());
				mav.addObject("msg3", r_member.getEmail());
				mav.addObject("msg4", session.getAttribute("user_id"));

				String id = (String) session.getAttribute("user_id");

				List<MFavorites> mFindFavorite = mFavoritesRepository.findFavorite(id);
				List<DFavorites> dFindFavorite = dFavoritesRepository.findFavorite(id);
				List<GFavorites> gFindFavorite = gFavoritesRepository.findFavorite(id);
				mav.addObject("mFindFavorite", mFindFavorite);
				mav.addObject("dFindFavorite", dFindFavorite);
				mav.addObject("gFindFavorite", gFindFavorite);

			} else {
				out.println("<script>alert('아이디 혹은 비밀번호가 다릅니다.'); history.go(-1);</script>");
				out.flush();
			}

			return mav;

		} catch (Exception e) {
			out.println("<script>alert('Unknown Error'); history.go(-1);</script>");
			out.flush();
			return mav;
		}
	}

	/* 회원가입 화면 구현 */
	@RequestMapping(value = "account/sc_join", method = RequestMethod.GET)
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/join");
		return mav;
	}

	/* 회원가입 로직 구현 */
	@RequestMapping(params = "db_join", value = { "account/sc_join" }, method = RequestMethod.POST)
	public String join(@ModelAttribute MemberForm form, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		R_member rmember = new R_member();

		Optional<String> id = Optional.ofNullable(form.getId());
		Optional<String> pass = Optional.ofNullable(form.getPass());
		Optional<String> pass2 = Optional.ofNullable(form.getPass2());
		Optional<String> email = Optional.ofNullable(form.getEmail());

		String idReg = "^[a-z]+[a-z0-9]{5,19}$";
		String passReg = "^(?=.*\\d)(?=.*[a-z]).{8,}$"; // 영문 숫자를 포함한 8글자 이상. 특문 제외 (?=.*[~`!@#$%\\^&*()-])
		Date today = new Date();

		/* ID CHECK */
		Optional<R_member> rMemberId = userRepository.findById(form.getId());
		if (id.get().isEmpty()) { // ID 공란 CHECK
			out.println("<script>alert('ID가 공란입니다.'); history.go(-1);</script>");
			out.flush();
		} else if (rMemberId.isPresent()) { // ID Duplicate CHECK
//			String DB_id = r_member.get().getId(); //Optional에서 get() 해서 ID를 한번 더 get해줌.
			out.println("<script>alert('이미 존재하는 ID입니다. 다른 아이디를 사용해 주세요.'); history.go(-1);</script>");
			out.flush();
		} else if (!form.getId().matches(idReg)) { // ID 정규식 CHECK
			out.println("<script>alert('아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자여야 합니다.'); history.go(-1);</script>");
			out.flush();
		}
		/* PASS CHECK */
		else if (pass.get().isEmpty()) { // PASS 공란 CHECK
			out.println("<script>alert('패스워드가 공란입니다.'); history.go(-1);</script>");
			out.flush();
			System.out.println("pass check OK");
		} else if (!form.getPass().matches(passReg)) { // ID 정규식 CHECK
			out.println("<script>alert('비밀번호는 영문자, 숫자를 포함하는 8글자 이상이어야 합니다.'); history.go(-1);</script>");
			out.flush();
		}

		/* PASS2 CHECK */
		else if (pass2.get().isEmpty()) { // NULL CHECK
			out.println("<script>alert('비밀번호를 한번 더 입력해주세요.'); history.go(-1);</script>");
			out.flush();
		} else if (!pass2.get().equals(pass.get())) { // pass1 = pass2?
			out.println("<script>alert('비밀번호 확인과 비밀번호의 값이 다릅니다.'); history.go(-1);</script>");
			out.flush();
		} else if (email.get().isEmpty()) { // NULL CHECK
			out.println("<script>alert('이메일을 입력해주세요.'); history.go(-1);</script>");
			out.flush();
		} else {

			rmember.setId(form.getId());
			rmember.setPass(form.getPass());
			rmember.setEmail(form.getEmail());
			rmember.setReg_date(today);

			userRepository.save(rmember);

			out.println("<script>alert('축하합니다! 회원가입에 성공하셨습니다.'); location.href = \"/\"; </script>");
			out.flush();
		}

		return "redirect:/";
	}

	/* 정보 수정 화면 구현 */
	@RequestMapping(params = "sc_modify", value = "account/modify", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute MemberForm form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("[CHECK] modify...(ok)");

		/* 현재 Session ID값을 구한다. */
		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");
		System.out.println("[CHECK] Session : " + s_id);

		/* Session ID에 해당하는 R_MEMBER 정보를 구한다. */
		R_member r_member1 = userRepository.findById(s_id).get();
		ModelAndView mav1 = new ModelAndView();

		mav1.setViewName("account/modify");
		mav1.addObject("no", r_member1.getNo());
		mav1.addObject("id", r_member1.getId());
		mav1.addObject("pass", r_member1.getPass());
		mav1.addObject("email", r_member1.getEmail());

		return mav1;
	}

	/* 정보 수정 로직 구현 */
	@RequestMapping(params = "db_modify", value = "account/modify", method = RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute R_member form, Model model, HttpServletRequest request) {
		System.out.println("[Check] r_member update Start...(ok)");
		System.out.println("[Check] r_member update Target PK No...(" + form.getNo() + ")");

		/* 업데이트할 대상 레코드를 검색한다. */
		R_member user = userRepository.findById(form.getNo()).get();

		/* DB update를 위한 레코드검색, 셋팅값 적용, 저장 실행 */
		// user.setNo(form.getNo());
		// user.setId(form.getId());
		user.setPass(form.getPass());
		user.setEmail(form.getEmail());
		user.setReg_date(new Date());

		userRepository.save(user);

		/* 현재 Session ID값을 구한다. */
		HttpSession session = request.getSession();
		String s_id = (String) session.getAttribute("user_id");

		System.out.println("[Check] r_member update Complete...(ok)");

		/* 수정된 내용을 mypage로 반환해서 확인할 수 있도록 한다. */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/mypage");
		mav.addObject("msg1", form.getId());
		mav.addObject("msg2", form.getPass());
		mav.addObject("msg3", form.getEmail());
		mav.addObject("msg4", s_id);
		
		List<MFavorites> mFindFavorite = mFavoritesRepository.findFavorite(s_id);
		List<DFavorites> dFindFavorite = dFavoritesRepository.findFavorite(s_id);
		List<GFavorites> gFindFavorite = gFavoritesRepository.findFavorite(s_id);
		mav.addObject("mFindFavorite", mFindFavorite);
		mav.addObject("dFindFavorite", dFindFavorite);
		mav.addObject("gFindFavorite", gFindFavorite);

		return mav;
	}

	/* 헤더 로그아웃 구현 */
	@RequestMapping(value = "account/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/");

		session.invalidate();

		return mav;
	}

	/* 로그아웃 로직 구현 */
	@RequestMapping(params = "logout", value = "account/modify", method = RequestMethod.POST)
	public ModelAndView logout(@ModelAttribute MemberForm form, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirAttrs) throws IOException {
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/");

		redirAttrs.addFlashAttribute("logoutMessage", "성공적으로 로그아웃 되었습니다.");
		System.out.println("Logout complete");

		/* 세션을 끊고 홈으로 돌아간다. */
		return mav;
	}

	/* 정보 삭제 로직 구현 */
	@RequestMapping(params = "del_member", value = "account/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute R_member form, HttpSession session) {
		System.out.println("삭제 메소드 del_member가 실행되었습니다.");

		/* 업데이트할 대상 레코드를 검색한다. */
		/* 실행 ID가 Admin인지 확인하는 절차 필요 */
		userRepository.deleteById(form.getNo());
		session.invalidate();
		
		return "redirect:/home";

	}

}

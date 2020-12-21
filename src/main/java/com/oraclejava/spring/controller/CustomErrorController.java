
//에러코드 검출하여 해당 에러 페이지 출력

package com.oraclejava.spring.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		ModelAndView modelAndView = new ModelAndView();
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				modelAndView.setViewName("/error/404");
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				modelAndView.setViewName("/error/500");
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				modelAndView.setViewName("/error/403");
			}else 
				modelAndView.setViewName("/error/common");
			
		}
		return modelAndView;
	}
	
	@Override
	public String getErrorPath() {
		return null;
	}
}

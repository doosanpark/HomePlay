
//메인 홈 화면 내 랜덤으로 영화 4개 화면 이미지 출력
//메인 홈 화면 하단부 장르별 영화 이미지 출력

package com.oraclejava.spring.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class HomeController {

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView body_center() {
		ModelAndView rec = new ModelAndView();
		rec.setViewName("home/main");
		List<Movie> lists = movieRepository.findAll();
		rec.addObject("movieRecommend", lists);

		Collections.shuffle(lists); // 리스트 내 값 랜덤으로 순서 재배치

		// 총 영화 리스트 중 4개 항목만 리스트에 저장
		for (int i = 4; i < lists.size(); i++) {
			lists.remove(i);

		}

		rec.addObject("movieRecommend", lists);

		List<Movie> movieLists = movieRepository.findGenre("로맨스", PageRequest.of(0, 4));
		List<Movie> movieLists2 = movieRepository.findGenre("액션", PageRequest.of(0, 4));
		List<Movie> movieLists3 = movieRepository.findGenre("애니메이션", PageRequest.of(0, 4));
		List<Movie> movieLists4 = movieRepository.findGenre("코미디", PageRequest.of(0, 4));
		rec.addObject("movieListRomance", movieLists);
		rec.addObject("movieListAction", movieLists2);
		rec.addObject("movieListAni", movieLists3);
		rec.addObject("movieListComi", movieLists4);

		return rec;
	}

}

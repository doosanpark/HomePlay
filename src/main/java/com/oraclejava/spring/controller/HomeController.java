//메인 홈 화면 내 랜덤으로 영화 4개 화면 이미지 출력
//메인 홈 화면 하단부 장르별 영화 이미지 출력

package com.oraclejava.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.spring.dao.drama.DramaRepository;
import com.oraclejava.spring.dao.game.GameRepository;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.model.drama.Drama;
import com.oraclejava.spring.model.movie.Movie;

@Controller
public class HomeController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private DramaRepository dramaRepository;

	@Autowired
	private GameRepository gameRepository;

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView body_center() {
		ModelAndView rec = new ModelAndView();
		rec.setViewName("home/main");

		List M_lists = movieRepository.findAll(); // 영화 리스트
		List D_lists = dramaRepository.findAll(); // 드라마 리스트
		List G_lists = gameRepository.findAll(); // 게임 리스트
//		Collections.shuffle(M_lists); // 영화 리스트 내 값 랜덤으로 순서 재배치
//		Collections.shuffle(D_lists); // 드라마 리스트 내 값 랜덤으로 순서 재배치

		// 총 영화 리스트 중 2개 항목만 리스트에 저장
		int M_listLen = M_lists.size();
		for (int i = 1; i <= M_listLen - 2; i++) {
			M_lists.remove(2);

		}

		// 총 드라마 리스트 중 1개 항목만 리스트에 저장
		int D_listLen = D_lists.size();
		for (int i = 1; i <= D_listLen - 1; i++) {
			D_lists.remove(1);

		}

		// 총 게임 리스트 중 1개 항목만 리스트에 저장
		int G_listLen = G_lists.size();
		for (int i = 1; i <= G_listLen - 1; i++) {
			G_lists.remove(1);

		}

		rec.addObject("movieRecommend", M_lists);
		rec.addObject("dramaRecommend", D_lists);
		rec.addObject("gameRecommend", G_lists);
//
//		List totalLists = new ArrayList();
//		totalLists.add(M_lists);
//		totalLists.add(D_lists);
//		Collections.shuffle(totalLists); // 영화 리스트 내 값 랜덤으로 순서 재배치
//		// 총 영화, 드라마 리스트 중 4개 항목만 리스트에 저장
//		int totalListLen = totalLists.size();
//		for (int i = 1; i <= 2; i++) {
//			for (int j = 1; j <= totalListLen - 4; j++) {
//				totalLists.remove(4);
//			}
//		}
//		rec.addObject("Recommend", totalLists);
//		System.out.println(totalLists);

		List<Movie> movieLists1 = movieRepository.findGenre("로맨스", PageRequest.of(0, 4));
		List<Movie> movieLists2 = movieRepository.findGenre("액션", PageRequest.of(0, 4));
		List<Drama> dramaLists1 = dramaRepository.findGenre("스릴러", PageRequest.of(0, 4));
		List<Drama> dramaLists2 = dramaRepository.findGenre("코미디", PageRequest.of(0, 4));

		rec.addObject("movieListRomance", movieLists1);
		rec.addObject("movieListAction", movieLists2);
		rec.addObject("dramaThriller", dramaLists1);
		rec.addObject("dramaComedy", dramaLists2);

		return rec;
	}

}

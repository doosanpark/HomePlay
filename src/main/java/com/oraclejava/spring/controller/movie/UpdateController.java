package com.oraclejava.spring.controller.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class UpdateController {

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(path = "/movie/updatePre/{no}", method = RequestMethod.GET)
	public String index(@PathVariable Integer no, Model model) {
		
		Movie movie = movieRepository.findById(no).get();
		MovieForm form = new MovieForm();
		movie.setTitle(form.getTitle());
		movie.setGenre(form.getGenre());
		movie.setSummary_title(form.getSummary_title());
		movie.setSummary_content(form.getSummary_content());
		movie.setAge_limit(form.getAge_limit());
//		movie.setPoster();
//		movie.setThumbnail();
		movie.setScreening(form.getScreening());
		
		return "/movie/update";
	}

	
}

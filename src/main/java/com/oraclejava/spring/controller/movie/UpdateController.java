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

	@RequestMapping(path = "/movie/updatepre/{no}", method = RequestMethod.GET)
	public String index(@PathVariable Integer no, Model model) {
		
		Movie movie = movieRepository.findById(no).get();
		MovieForm form = new MovieForm();
		form.setTitle(movie.getTitle());
		form.setGenre(movie.getGenre());
		form.setSummary_title(movie.getSummary_title());
		form.setSummary_content(movie.getSummary_content());
		form.setAge_limit(movie.getAge_limit());
//		movie.setPoster();
//		movie.setThumbnail();
		form.setScreening(movie.getScreening());
		model.addAttribute("movie", form);
		
		System.out.println("title: "+ form.getTitle());
		
		
		return "movie/updatePre";
	}

	
}

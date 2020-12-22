package com.oraclejava.spring.controller.movie;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oraclejava.spring.dao.drama.DramaRepository;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.model.drama.Drama;
import com.oraclejava.spring.model.movie.Movie;

@Controller
public class CreateController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private DramaRepository dramaRepository;
	
	
	
	@Value("${img.absolute.location}")
	private String imageLocation;

	// contents 추가완료
	@RequestMapping(path = "movie/create", method = RequestMethod.GET)
	public String indexTop() {

		return "/movie/create";
	}

	// contents DB추가
	@RequestMapping(path = "movie/create", method = RequestMethod.POST)
	public String MovieCreate(@ModelAttribute MovieForm form, BindingResult bindingResult, Model model,
			String genre_movie, String genre_drama)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "redirect:/movie/create";
		}
		if(genre_movie.equals("")) {
			genre_movie = null;
		}
		if(genre_drama.equals("")) {
			genre_drama = null;
		}
		
		System.out.println(genre_movie);
		String sourceFileName = form.getThumbnail_image().getOriginalFilename();
		MultipartFile sourceFile = form.getThumbnail_image();
		File destFile = new File(imageLocation+ sourceFileName);
		sourceFile.transferTo(destFile);
		
		String sourceFileName1 = form.getPoster_image().getOriginalFilename();
		MultipartFile sourceFile1 = form.getPoster_image();
		File destFile1 = new File(imageLocation+ sourceFileName1);
		sourceFile1.transferTo(destFile1);

		if (genre_movie != null) {
			Movie movie = new Movie();
			movie.setTitle(form.getTitle());
			movie.setGenre(genre_movie);
			movie.setSummary_title(form.getSummary_title());
			movie.setSummary_content(form.getSummary_content());
			movie.setAge_limit(form.getAge_limit());
			movie.setPoster(sourceFileName1);
			movie.setThumbnail(sourceFileName);
			movie.setScreening(form.getScreening());
			movie.setReg_date(new Date());
			movieRepository.save(movie);
			
			return "/movie/createSuccess";
		}else if(genre_drama != null) {
			Drama drama = new Drama();
			drama.setTitle(form.getTitle());
			drama.setGenre(genre_drama);
			drama.setSummary_title(form.getSummary_title());
			drama.setSummary_content(form.getSummary_content());
			drama.setAge_limit(form.getAge_limit());
			drama.setPoster(sourceFileName1);
			drama.setThumbnail(sourceFileName);
			drama.setScreening(form.getScreening());
			drama.setReg_date(new Date());
			dramaRepository.save(drama);
			
			return "/movie/createSuccess";
		}else {
			
			return "/";
		}
	}
	

	
}

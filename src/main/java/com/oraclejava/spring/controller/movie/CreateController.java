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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oraclejava.spring.bean.movie.Movie;
import com.oraclejava.spring.dao.movie.MovieRepository;

@Controller
public class CreateController {
	@Autowired
	private MovieRepository movieRepository;

	@Value("img.absolute.location")
	private String imageLocation;

	// contents 추가완료
	@RequestMapping(path = "movie/create", method = RequestMethod.GET)
	public String indexTop() {

		return "/movie/create";
	}

	// contents DB추가
	@RequestMapping(path = "movie/create", method = RequestMethod.POST)
	public String MovieCreate(@ModelAttribute MovieForm form, BindingResult bindingResult, Model model)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "redirect:/movie/create";
		}
		
		String sourceFileName = form.getThumbnail_image().getOriginalFilename();
		MultipartFile sourceFile = form.getThumbnail_image();
		File destFile = new File(imageLocation+ sourceFileName);
		sourceFile.transferTo(destFile);
		
		String sourceFileName1 = form.getPoster_image().getOriginalFilename();
		MultipartFile sourceFile1 = form.getPoster_image();
		File destFile1 = new File(imageLocation+ sourceFileName1);
		sourceFile1.transferTo(destFile1);


		Movie movie = new Movie();
		movie.setTitle(form.getTitle());
		movie.setGenre(form.getGenre());
		movie.setSummary_title(form.getSummary_title());
		movie.setSummary_content(form.getSummary_content());
		movie.setAge_limit(form.getAge_limit());
		movie.setPoster(sourceFileName1);
		movie.setThumbnail(sourceFileName);
		movie.setScreening(form.getScreening());
		movie.setReg_date(new Date());
		movieRepository.save(movie);
		return "/movie/createSuccess";
		
	}
	

	
}

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

import com.oraclejava.spring.controller.ContentsForm;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.model.movie.Movie;

@Controller
public class UpdateController {

	@Value("${img.absolute.location}")
	private String imageLocation;

	@Autowired
	private MovieRepository movieRepository;

	// 폰 수정전 현재 DB에 입력된 값 출력
	@RequestMapping(path = "/create/update/pre/{no}", method = RequestMethod.GET)
	public String updatePre(@PathVariable Integer no, Model model) {

		System.out.println("랄랄ㄹ라");

		Movie movie = movieRepository.findById(no).get();
		ContentsForm form = new ContentsForm();
		form.setNo(movie.getNo());
		form.setTitle(movie.getTitle());
		form.setGenre(movie.getGenre());
		form.setSummary_title(movie.getSummary_title());
		form.setSummary_content(movie.getSummary_content());
		form.setAge_limit(movie.getAge_limit());
		form.setPoster(movie.getPoster());
		form.setThumbnail(movie.getThumbnail());
		form.setScreening(movie.getScreening());
		model.addAttribute("movie", form);

		return "/create/update";
	}

	// 폰 수정
	@RequestMapping(path = "/create/update/pre/{no}", method = RequestMethod.POST)
	public String contentsUpdate(@ModelAttribute ContentsForm form, BindingResult bindingResult, Model model)
			throws IllegalStateException, IOException {

		System.out.println("울울울");

		if (bindingResult.hasErrors()) {
			return "redirect:/create/update/pre/" + form.getNo();
		}

		Movie movie = new Movie();

		String thumbnailFileName = form.getThumbnail_image().getOriginalFilename();
		MultipartFile thumbnailFile = form.getThumbnail_image();
		File thumbnail = new File(imageLocation + thumbnailFileName);
		thumbnailFile.transferTo(thumbnail);

		String posterFileName = form.getPoster_image().getOriginalFilename();
		MultipartFile posterFile = form.getPoster_image();
		File poster = new File(imageLocation + posterFileName);
		posterFile.transferTo(poster);

		movie.setNo(form.getNo());
		movie.setTitle(form.getTitle());
		movie.setGenre(form.getGenre());
		movie.setSummary_title(form.getSummary_title());
		movie.setSummary_content(form.getSummary_content());
		movie.setAge_limit(form.getAge_limit());
		movie.setPoster(form.getPoster());
		movie.setThumbnail(form.getThumbnail());
		movie.setScreening(form.getScreening());
		movie.setReg_date(new Date());
		movieRepository.save(movie);

		return "/create/updateSuccess";

	}

}

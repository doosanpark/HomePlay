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

import com.oraclejava.spring.controller.ContentsForm;
import com.oraclejava.spring.dao.drama.DramaRepository;
import com.oraclejava.spring.dao.movie.MovieRepository;
import com.oraclejava.spring.dao.movie.StaffRepository;
import com.oraclejava.spring.model.contents.RStaff;
import com.oraclejava.spring.model.movie.Movie;

@Controller
public class CreateController {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private DramaRepository dramaRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Value("${img.absolute.location}")
	private String imageLocation;

	// contents 추가완료
	@RequestMapping(path = "content/create", method = RequestMethod.GET)
	public String indexTop() {

		return "/create/create";
	}

	// contents DB추가
	@RequestMapping(path = "content/create", method = RequestMethod.POST)
	public String MovieCreate(@ModelAttribute ContentsForm form, BindingResult bindingResult, Model model)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "redirect:/create/create";
		}
		/*
		 * if(genre_movie.equals("")) { genre_movie = null; } if(genre_drama.equals(""))
		 * { genre_drama = null; }
		 */
		
		//썸네일 저장
		String thumbnailName = form.getThumbnail_image().getOriginalFilename();
		MultipartFile thumnail = form.getThumbnail_image();
		File destThumbnail = new File(imageLocation+ thumbnailName);
		thumnail.transferTo(destThumbnail);
		
		//포스터 저장
		String posterName = form.getPoster_image().getOriginalFilename();
		MultipartFile poster = form.getPoster_image();
		File destPoster = new File(imageLocation+posterName);
		poster.transferTo(destPoster);
		
		//스태프 사진'들'저장
//		MultipartFile[] photos = form.getS_photo();
//		String[] names = form.getS_name();
//		String[] roles = form.getS_role();
//		for(int i = 0; i < photos.length; i++) {
//			String photoName = photos[i].getOriginalFilename();
//			MultipartFile photo = photos[i];
//			File destPhoto = new File(imageLocation+"photo/"+  photoName);
//			photo.transferTo(destPhoto);
//			
//			String name=names[i];
//			String role=roles[i];
//			
//			
//			RStaff staff = new RStaff();
//			String category="";
//			
//			switch(form.getCategory()) {
//			case "movie":
//				category="m";
//				break;
//			case "drama":
//				category="d";
//				break;
//			case "game":
//				category="g";
//				break;
//			default :
//				break;
//			}
//			
//			staff.setCategory(category);
//			staff.setTitle(form.getTitle());
//			staff.setName(name);
//			staff.setNo(form.getNo());
//			staff.setPhoto(photoName);
//			staff.setReg_date(new Date());
//			staff.setRole(role);
//			staffRepository.save(staff);
//		}
		
		Movie movie = new Movie();
		movie.setTitle(form.getTitle());
		movie.setGenre(form.getGenre());
		movie.setSummary_title(form.getSummary_title());
		movie.setSummary_content(form.getSummary_content());
		movie.setAge_limit(form.getAge_limit());
		movie.setPoster(posterName);
		movie.setThumbnail(thumbnailName);
		movie.setScreening(form.getScreening());
		movie.setReg_date(new Date());
		movieRepository.save(movie);
	
		return "/create/createSuccess";
		
		

//		if (genre_movie != "") {
//			Movie movie = new Movie();
//			movie.setTitle(form.getTitle());
//			movie.setGenre(genre_movie);
//			movie.setSummary_title(form.getSummary_title());
//			movie.setSummary_content(form.getSummary_content());
//			movie.setAge_limit(form.getAge_limit());
//			movie.setPoster(sourceFileName1);
//			movie.setThumbnail(sourceFileName);
//			movie.setScreening(form.getScreening());
//			movie.setReg_date(new Date());
//			movieRepository.save(movie);
//			
//			return "/create/createSuccess";
//		}else if(genre_drama != "") {
//			Drama drama = new Drama();
//			drama.setTitle(form.getTitle());
//			drama.setGenre(genre_drama);
//			drama.setSummary_title(form.getSummary_title());
//			drama.setSummary_content(form.getSummary_content());
//			drama.setAge_limit(form.getAge_limit());
//			drama.setPoster(sourceFileName1);
//			drama.setThumbnail(sourceFileName);
//			drama.setScreening(form.getScreening());
//			drama.setReg_date(new Date());
//			dramaRepository.save(drama);
//			
//			return "/create/createSuccess";
//		}else {
//			
//			return "/";
//		}
	}
	

	
}

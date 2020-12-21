package com.oraclejava.spring.controller.drama;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DramaForm {
	
	private String title;
	private String genre;
	private String summary_title;
	private String summary_content;
	private String age_limit;
	private String poster;
	private String thumbnail;
	private String screening;
	private MultipartFile thumbnail_image;
	private MultipartFile poster_image;
}

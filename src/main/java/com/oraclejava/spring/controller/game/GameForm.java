package com.oraclejava.spring.controller.game;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameForm {
	private int no;
	private String title;
	private String genre;
	private String summary_title;
	private String summary_content;
	private String age_limit;
	private String poster;
	private String thumbnail;
	private String reg_date;
	private String release;
	private MultipartFile thumbnail_image;
	private MultipartFile poster_image;
}

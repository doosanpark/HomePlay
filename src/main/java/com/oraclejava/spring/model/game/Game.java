package com.oraclejava.spring.model.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name ="R_game")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "gameIdGenerator")
	@SequenceGenerator(name = "gameIdGenerator",
	sequenceName = "game_no_seq",
	allocationSize = 1)
	private int no;
	private String title;
	private String genre;
	private String summary_title;
	private String summary_content;
	private String age_limit;
	private String poster;
	private String thumbnail;
	private String screening;
	private String reg_date;
	
}

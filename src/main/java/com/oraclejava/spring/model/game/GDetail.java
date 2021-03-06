package com.oraclejava.spring.model.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.oraclejava.spring.model.movie.Favorite;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "R_GAME")
@Getter
@Setter
public class GDetail { 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "movieNoGenerator")
	@SequenceGenerator(name = "movieNoGenerator",
		sequenceName = "MOVIE_NO_SEQ",
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
	
	/*
	 * public Detail(int nO, String title, String genre, String summary_title,
	 * String summary_content, int age_limit, String poster, String thumbnail,
	 * String screening, int reg_date) { super(); NO = nO; this.title = title;
	 * this.genre = genre; this.summary_title = summary_title; this.summary_content
	 * = summary_content; this.age_limit = age_limit; this.poster = poster;
	 * this.thumbnail = thumbnail; this.screening = screening; this.reg_date =
	 * reg_date; }
	 */
	
	public GDetail() {
	}

	@OneToMany(mappedBy = "detail", cascade = CascadeType.ALL)  
	@OrderBy("reg_date desc")
	private List<GReview> review; // r_review 테이블과 조인
	
	@OneToMany(mappedBy = "detail", cascade = CascadeType.ALL)  
	private List<Favorite> favorites; // r_review 테이블과 조인
	
	
	
}

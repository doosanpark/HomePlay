package com.oraclejava.spring.bean.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="R_movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "movieIdGenerator")
	@SequenceGenerator(name = "movieIdGenerator",
	sequenceName = "movie_no_seq",
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
    private Date reg_date;
    
    
}

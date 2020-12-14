package com.oraclejava.spring.model.movie;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieBean {
	
	 @Id
	   @GeneratedValue(strategy = GenerationType.SEQUENCE,
	   generator = "movieIdGenerator")
	   @SequenceGenerator(name = "movieIdGenerator",
	   sequenceName = "movie_NO_seq",
	   allocationSize = 1)
	   private int NO;
	    private String TITLE;
	    private String GENRE;
	    private String SUMMARY_TITLE;
	    private String SUMMARY_CONTENT;
	    private String AGE_LIMIT;
	    private String POSTER;
	    private String THUMBNAIL;
	    private String SCREENING;
	    private String REG_DATE;

}

package com.oraclejava.spring.bean.movie;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Drama {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "dramaIdGenerator")
	@SequenceGenerator(name = "dramaIdGenerator",
	sequenceName = "drama_no_seq",
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

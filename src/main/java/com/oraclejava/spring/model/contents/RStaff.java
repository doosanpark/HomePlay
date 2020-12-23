package com.oraclejava.spring.model.contents;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "r_staff")
@Getter
@Setter
public class RStaff {

	@Id 
	private int no;
	private String category;
	private int movie_no;
	private String movie_title;
	private String name;
	private String role;
	private String photo;
	private Date reg_date;
	
}

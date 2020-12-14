package com.oraclejava.spring.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class R_member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "memberGenerator")
	@SequenceGenerator(name = "memberGenerator",
					   sequenceName = "MEMBER_NO_SEQ",
					   allocationSize = 1)
	private int no;
	private String id;
	private String pass;
	private String email;
	private Date date;
	
	public R_member() {}
	
	public R_member(int no, String id, String pass, String email, Date date) {
		super();
		this.no = no;
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.date = date;
	}
	
	
}

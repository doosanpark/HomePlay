package com.oraclejava.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class R_member {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "sr_member")
	@SequenceGenerator(name = "sr_member", sequenceName = "MEMBER_NO_SEQ", allocationSize = 1)
	@Id
	private int no;
	
	private String id;
	private String pass;
	private String email;
	private Date reg_date;
	
	public R_member() {}
	
	public R_member(int no, String id, String pass, String email, Date reg_date) {
		super();
		this.no = no;
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.reg_date = reg_date;
	}

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}

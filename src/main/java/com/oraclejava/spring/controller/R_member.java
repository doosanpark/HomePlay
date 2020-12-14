package com.oraclejava.spring.controller;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class R_member {
	@Id
	private String id;
	private String pass;
	private String email;
	
	public R_member() {
	}
	
	public R_member(String id, String pass, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.email = email;
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
	public void setPwd(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}

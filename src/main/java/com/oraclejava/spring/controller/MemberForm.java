package com.oraclejava.spring.controller;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
	private int no;
	private String id;
	private String pass;
	private String pass2;
	private String email;
	private Date reg_date;
}
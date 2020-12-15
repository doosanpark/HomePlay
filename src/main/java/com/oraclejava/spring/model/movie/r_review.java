package com.oraclejava.spring.model.movie;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class r_review {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ris")
	@SequenceGenerator(name = "ris", sequenceName = "review_id_sequence", allocationSize = 1)
	private int no;
	
	private int member_no;
	private String title;
	private String content;
	private int grade;
	private Date reg_date;

	@ManyToOne //다대일
	@JoinColumn(name = "movie_no") // Detail 테이블의 no column과 조인
	private Detail detail;
	
	public r_review() {
		
	}

	public r_review(int member_no, String title, String content, int grade, Date reg_date) {
		this.member_no = member_no;
		this.title = title;
		this.content = content;
		this.grade = grade;
		this.reg_date = reg_date;
	}

	public r_review(int member_no, String title, String content, int grade, Date reg_date,
			Detail detail) {
		this.member_no = member_no;
		this.title = title;
		this.content = content;
		this.grade = grade;
		this.reg_date = reg_date;
		this.detail = detail;
	}
	
}

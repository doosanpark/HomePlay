package com.oraclejava.spring.model.movie;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "r_review")
public class RReview {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ris")
	@SequenceGenerator(name = "ris", sequenceName = "review_no_seq", allocationSize = 1)
	private int no;
	
	private int member_no;
	private String title;
	private String content;
	private int grade;
	private Date reg_date;
	private String member_id;

	@ManyToOne //다대일
	@JoinColumn(name = "movie_no") // Detail 테이블의 no column과 조인
	private MDetail detail;
	
	public RReview() {
		
	}

	public RReview(int member_no, String title, String content, int grade, Date reg_date) {
		this.member_no = member_no;
		this.title = title;
		this.content = content;
		this.grade = grade;
		this.reg_date = reg_date;
	}

	public RReview(int member_no, String title, String content, int grade, Date reg_date,
			MDetail detail, String member_id) {
		this.member_no = member_no;
		this.title = title;
		this.content = content;
		this.grade = grade;
		this.reg_date = reg_date;
		this.detail = detail;
		this.member_id = member_id;
	}
	
}

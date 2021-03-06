package com.oraclejava.spring.model.game;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="v_fmg")
public class GFavorites {
	@Id
	private int sn;
	private String id;
	private int rno;
	private int no;
    private String title;
    private String genre;
    private String summary_title;
    private String summary_content;
    private String age_limit;
    private String poster;
    private String thumbnail;
    private String screening;
    private String reg_date;
	
}

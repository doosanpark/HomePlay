package com.oraclejava.spring.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "R_favorites")
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_FAVORITES_SN_SEQ")
	@SequenceGenerator(name = "R_FAVORITES_SN_SEQ", sequenceName = "R_FAVORITES_SN_SEQ", allocationSize = 1)
	private int sn;
	
	private String id;
	
	private int rno;

	public Favorite(String id, int rno) {
		this.id = id;
		this.rno = rno;
	}
	
}

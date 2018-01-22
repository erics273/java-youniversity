package com.youniversity.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Major {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private Integer minimunSat;
    
    public Major() {
	} 
    
    public Major(String major, Integer minimunSat) {
		this.major = major;
		this.minimunSat = minimunSat;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getMinimunSat() {
		return minimunSat;
	}

	public void setMinimunSat(Integer minimunSat) {
		this.minimunSat = minimunSat;
	}
        

}

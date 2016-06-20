package com.srx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.srx.model.base.BaseModel;

@Entity(name="SRX_CATEGORY")
public class Category extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3585279885585794671L;
	@Column(name="NAME")
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

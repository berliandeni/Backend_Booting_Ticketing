package com.lawencon.booting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_m_companies")
public class Companies extends BaseModel{

	@NotNull
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@Column(nullable = false, unique = true)
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
package com.lawencon.booting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_m_classifications")
public class Classifications extends BaseModel{
	
	@NotNull
	@Column(nullable = false, unique = true)
	private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

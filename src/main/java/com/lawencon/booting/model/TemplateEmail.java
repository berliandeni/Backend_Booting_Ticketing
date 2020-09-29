package com.lawencon.booting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_template_emails")
public class TemplateEmail extends BaseModel{

	private String code;
	private String name;
	
	@Column(columnDefinition="TEXT")
	private String template;

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
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}

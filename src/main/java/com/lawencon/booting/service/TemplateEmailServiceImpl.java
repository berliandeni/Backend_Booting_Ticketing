package com.lawencon.booting.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.TemplateEmailDao;
import com.lawencon.booting.model.TemplateEmail;


@Service
@Transactional
public class TemplateEmailServiceImpl implements TemplateEmailService{

	@Autowired
	private TemplateEmailDao templateEmailDao;
	
	@Override
	public TemplateEmail insert(TemplateEmail data) throws Exception {
		data.setCreatedAt(new Date());
		return templateEmailDao.insert(data);
	}

	@Override
	public TemplateEmail getTemplateEmailByCode(TemplateEmail code) throws Exception {
		return templateEmailDao.getTemplateEmailByCode(code.getCode());
	}

}

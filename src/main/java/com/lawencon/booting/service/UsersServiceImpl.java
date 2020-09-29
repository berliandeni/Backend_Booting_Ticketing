package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.booting.dao.UsersDao;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.TemplateEmail;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.utility.Mail;

@Service
@Transactional
public class UsersServiceImpl extends BaseService implements UsersService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private AccountsService accountsService;

//	@Autowired
//	private RolesService rolesService;
//
//	@Autowired
//	private CompaniesService companiesService;

//	@Autowired
//	private PhotoProfileService photoProfileService;

	@Autowired
	private Mail mail;

	@Autowired
	private TemplateEmailService templateEmailService;

	@Override
	public Users insert(Users data) throws Exception {
		data.setCreatedAt(new Date());
		data.setVersion(1L);
		return usersDao.insert(data);
	}

	@Override
	public Users update(Users data, MultipartFile file) throws Exception {
		data.setUpdatedAt(new Date());
		TemplateEmail template = new TemplateEmail();
		template.setCode("PROTMPL");
		template = templateEmailService.getTemplateEmailByCode(template);
		Accounts acc = new Accounts();
//		Roles roles = new Roles();
//		Companies company = new Companies();
//		company = companiesService.getCompanyByName(data.getIdCompany());
//		roles = rolesService.getRolesByCode(data.getIdRole());
		acc.setIdUser(data);
		acc = accountsService.findByUser(acc);
//		if(file != null) {
//			PhotoProfile photo = new PhotoProfile();
//			photo = photoProfileService.store(file);
//			data.setIdPhoto(photo);
//		}
//		data.setId(us.getId());
//		data.setIdCompany(us.getIdCompany());
//		data.setIdRole(us.getIdRole());
//		data.setIdRole(roles);
//		data.setIdCompany(company);
		mail.init(acc.getEmail(), "Profile Updated", template.getTemplate(), data.getName()).sendMail();
		data.setUpdatedAt(new Date());
		Users us = getUserByNip(data);
		us = check(us, data);
		return usersDao.update(data);
	}

	@Override
	public List<Users> getListUsers() throws Exception {
		return usersDao.getListUsers();
	}

	@Override
	public List<Users> getListUsersActive() throws Exception {
		return usersDao.getListUsersActive();
	}

	@Override
	public void delete(String data) throws Exception {
		accountsService.delete(data);
		usersDao.delete(data);
	}

	@Override
	public Users getUserByNip(Users data) throws Exception {
		return usersDao.getUserByNip(data);
	}

	@Override
	public void deletePath(String id) throws Exception {
		usersDao.deletePath(id);
	}

	@Override
	public List<Users> getListUsersByClient(Users data) throws Exception {
		return usersDao.getListUsersByClient(data);
	}
	
	@Override
	public List<Users> getListCustomers() throws Exception {
		return usersDao.getListCustomers();
	}

	@Override
	public List<Users> getListAgents() throws Exception {
		return usersDao.getListAgent();
	}

	@Override
	public List<Users> getListClients() throws Exception {
		return usersDao.getListClients();
	}

}

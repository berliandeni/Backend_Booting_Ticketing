package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.RolesDao;
import com.lawencon.booting.model.Roles;

@Service
@Transactional
public class RolesServiceImpl extends BaseService implements RolesService{

	@Autowired
	private RolesDao rolesDao;

	@Override
	public Roles insert(Roles data) throws Exception {
		data.setCreatedAt(new Date());
		data.setVersion(1L);
		return rolesDao.insert(data);
	}

	@Override
	public Roles update(Roles data) throws Exception {
		data.setUpdatedAt(new Date());
		Roles rl = getRolesByCode(data);
		data = check(rl, data);
		return rolesDao.update(data);
	}

	@Override
	public List<Roles> getListRoles() throws Exception {
		return rolesDao.getListRoles();
	}

	@Override
	public void deleteRoles(String id) throws Exception {
		rolesDao.deleteRoles(id);
		
	}

	@Override
	public Roles getRolesByCode(Roles code) throws Exception {
		return rolesDao.getRolesByCode(code.getCode());
	}
}

package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ClientProductsDao;
import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Products;

@Service
@Transactional
public class ClientProductsServiceImpl extends BaseService implements ClientProductsService {

	@Autowired
	private ClientProductsDao clientProductsDao;
	
	@Autowired
	private CompaniesService companiesService;

	@Autowired
	private ProductsService productService;
	
	@Override
	public ClientProducts insert(ClientProducts data) throws Exception {
		Companies comp = companiesService.getCompanyByCode(data.getIdCompany());
		data.setIdCompany(comp);
		
		Products prod = productService.getProductsByCode(data.getIdProduct());
		data.setIdProduct(prod);
		
		data.setCreatedAt(new Date());
		data.setVersion(1L);
		data.setTicketUrgent(3);
		data.setTicketMedium(7);
		return clientProductsDao.insert(data);
	}

	@Override
	public ClientProducts update(ClientProducts data) throws Exception {
		data.setUpdatedAt(new Date());
		ClientProducts cp = getData(data);
		data = check(cp, data);
		return clientProductsDao.update(data);
	}
	
	

	@Override
	public List<ClientProducts> getListClientProducts() throws Exception {
		return clientProductsDao.getListClientProducts();
	}

	@Override
	public void delete(String id) throws Exception {
		clientProductsDao.delete(id);
	}
	
	@Override
	public void deletePath(String id) throws Exception {
		clientProductsDao.deletePath(id);
	}

	@Override
	public List<ClientProducts> getListByCompany(ClientProducts data) throws Exception {
		Companies comp = companiesService.getCompanyByCode(data.getIdCompany());
		data.setIdCompany(comp);
		return clientProductsDao.getListByCompany(data);
	}

	@Override
	public List<String> getListIdCompany(Companies data) throws Exception {
		return clientProductsDao.getListIdCompany(data);
	}

	@Override
	public ClientProducts getData(ClientProducts data) throws Exception {
		return clientProductsDao.getData(data);
	}

	
}

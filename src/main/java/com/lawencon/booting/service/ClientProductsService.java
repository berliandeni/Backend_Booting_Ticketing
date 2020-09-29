package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;

public interface ClientProductsService {

	ClientProducts insert(ClientProducts data) throws Exception;

	ClientProducts update(ClientProducts data) throws Exception;

	List<ClientProducts> getListClientProducts() throws Exception;
	
	ClientProducts getData(ClientProducts data) throws Exception;

	void delete(String id) throws Exception;
	
	public void deletePath(String id) throws Exception;
	
	List<ClientProducts> getListByCompany(ClientProducts data) throws Exception;
	
	List<String> getListIdCompany(Companies data) throws Exception;
		
}

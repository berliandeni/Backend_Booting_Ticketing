package com.lawencon.booting.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;

@Repository
public class ClientProductsDaoImpl extends BaseDao implements ClientProductsDao {

	@Override
	public ClientProducts insert(ClientProducts data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public ClientProducts update(ClientProducts data) throws Exception {
		return em.merge(data);
	}

	@Override
	public List<ClientProducts> getListClientProducts() throws Exception {
		return em.createQuery("FROM ClientProducts ORDER BY createdAt DESC", ClientProducts.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from ClientProducts where id = :id")
		.setParameter("id", id).executeUpdate();
	}
	
	@Override
	public void deletePath(String id) throws Exception {
		em.createQuery("UPDATE ClientProducts SET active = false where id = :id")
		.setParameter("id", id).executeUpdate();
	}

	@Override
	public List<ClientProducts> getListByCompany(ClientProducts data) throws Exception {
		return em.createQuery("FROM ClientProducts WHERE idCompany.id = :idCompany ORDER BY createdAt DESC", ClientProducts.class)
				.setParameter("idCompany", data.getIdCompany().getId()).getResultList();
	}

	@Override
	public List<String> getListIdCompany(Companies data) throws Exception {
		List<Object> listData = em
				.createQuery("SELECT idProducts.id FROM ClientProducts WHERE idCompany.id = :id ORDER BY createdAt DESC", Object.class)
				.setParameter("id", data.getId()).getResultList();
		List<String> ListProducts = new ArrayList<>();
		listData.forEach(l -> {
			ListProducts.add(l.toString());
		});
		return ListProducts;
	}

	@Override
	public List<ClientProducts> getListClientProductsActive() throws Exception {
		return em.createQuery("FROM ClientProducts WHERE active = TRUE ORDER BY createdAt DESC", ClientProducts.class).getResultList();
	}

	@Override
	public ClientProducts getData(ClientProducts data) throws Exception {
		return em.createQuery("FROM ClientProducts WHERE idProduct.id = :idP AND idCompany.id = :idC",
						ClientProducts.class)
				.setParameter("idP", data.getIdProduct().getId()).setParameter("idC", data.getIdCompany().getId()).getSingleResult();
	}

}

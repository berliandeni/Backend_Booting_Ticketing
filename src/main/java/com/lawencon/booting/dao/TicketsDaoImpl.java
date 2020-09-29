package com.lawencon.booting.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.utility.Hibernate;

@Repository
public class TicketsDaoImpl extends BaseDao implements TicketsDao {

	@Override
	public Tickets insert(Tickets data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<Tickets> getListTickets() throws Exception {
		return em.createQuery("FROM Tickets ORDER BY createdAt DESC", Tickets.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from Tickets where id = :id").setParameter("id", id);
	}

	@Override
	public List<Tickets> getListByIdUser(String data) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.id = :id ORDER BY createdAt DESC", Tickets.class)
				.setParameter("id", data).getResultList();
	}

	@Override
	public List<Tickets> getListByIdCompany(String data) throws Exception {
		List<Tickets> a = em
				.createQuery("FROM Tickets WHERE idCustomer.idCompany.id = :id ORDER BY createdAt DESC", Tickets.class)
				.setParameter("id", data).getResultList();

		return a;
	}

	@Override
	public List<Tickets> getListByIdAgent(List<String> listData) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id IN (:id) ORDER BY createdAt DESC",
				Tickets.class).setParameter("id", listData).getResultList();
	}

	@Override
	public TicketStatus selectStatus() throws Exception {
		List<Object[]> obj = em
				.createQuery("SELECT COUNT(id), idStatus.code FROM Tickets GROUP BY idStatus.code", Object[].class)
				.getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCharts> getListTicketCharts(Long data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tms.name, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 1 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as January, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 2 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as February, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 3 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as March, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 4 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as April, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 5 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as May, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 6 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as June, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 7 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as July, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 8 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as August, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 9 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as September, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 10 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as October, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 11 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as November, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 12 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as December ");
		sql.append("FROM tb_m_status tms ORDER BY tms.name");

		List<Object[]> listData = em.createNativeQuery(sql.toString()).setParameter("year", data).getResultList();
		List<TicketCharts> listCharts = new ArrayList<>();

		listData.forEach(l -> {
			TicketCharts ticket = new TicketCharts();
			ticket.setName((String) l[0]);
			ticket.setJanuary((BigInteger) l[1]);
			ticket.setFebruary((BigInteger) l[2]);
			ticket.setMarch((BigInteger) l[3]);
			ticket.setApril((BigInteger) l[4]);
			ticket.setMay((BigInteger) l[5]);
			ticket.setJune((BigInteger) l[6]);
			ticket.setJuly((BigInteger) l[7]);
			ticket.setAugust((BigInteger) l[8]);
			ticket.setSeptember((BigInteger) l[9]);
			ticket.setOctober((BigInteger) l[10]);
			ticket.setNovember((BigInteger) l[11]);
			ticket.setDecember((BigInteger) l[12]);
			listCharts.add(ticket);
		});
		return listCharts;
	}

	@Override
	public TicketStatus statusAgent(List<String> listData) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.idCompany.id IN (:id) GROUP BY idStatus.code",
				Object[].class).setParameter("id", listData).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
		return ticket;
	}

	@Override
	public TicketStatus statusClient(String data) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.idCompany.id = :id GROUP BY idStatus.code",
				Object[].class).setParameter("id", data).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
		return ticket;
	}

	@Override
	public TicketStatus statusCustomer(String data) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.id = :id GROUP BY idStatus.code",
				Object[].class).setParameter("id", data).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
		return ticket;
	}

	@Override
	public Tickets getTicketByCode(Tickets data) throws Exception {
		List<Tickets> listData = em.createQuery("FROM Tickets WHERE code = :code", Tickets.class)
				.setParameter("code", data.getCode()).getResultList();
		return !listData.isEmpty() ? listData.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCharts> getChartsByClient(Companies data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tms.name, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 1 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as January ,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 2 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as February ,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 3 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as March, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 4 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as April, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 5 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as May, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 6 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as June , ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 7 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as July, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 8 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as August,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 9 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as September,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 10 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as October,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 11 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as November,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 12 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.name = :company ) as December ");
		sql.append("FROM tb_m_status tms ORDER BY tms.name");

		List<Object[]> listData = em.createNativeQuery(sql.toString()).setParameter("company", data.getName())
				.getResultList();
		List<TicketCharts> listCharts = new ArrayList<>();

		listData.forEach(l -> {
			TicketCharts ticket = new TicketCharts();
			ticket.setName((String) l[0]);
			ticket.setJanuary((BigInteger) l[1]);
			ticket.setFebruary((BigInteger) l[2]);
			ticket.setMarch((BigInteger) l[3]);
			ticket.setApril((BigInteger) l[4]);
			ticket.setMay((BigInteger) l[5]);
			ticket.setJune((BigInteger) l[6]);
			ticket.setJuly((BigInteger) l[7]);
			ticket.setAugust((BigInteger) l[8]);
			ticket.setSeptember((BigInteger) l[9]);
			ticket.setOctober((BigInteger) l[10]);
			ticket.setNovember((BigInteger) l[11]);
			ticket.setDecember((BigInteger) l[12]);
			listCharts.add(ticket);
		});
		return listCharts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCharts> getChartsByAgent(List<String> data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tms.name, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 1 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = date_part('year', current_timestamp)) ");
		sql.append("and com.id IN (:id) ) as January, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 2 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as February ,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 3 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as March, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 4 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as April, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 5 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as May, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 6 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as June , ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 7 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as July, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 8 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as August,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 9 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as September,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 10 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as October,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 11 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as November,  ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_companies com on com.id  = usr.id_company ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 12 ");
		sql.append("and tms.id = tms2.id ");
		sql.append("and extract (year from trht.created_at ) = date_part('year', current_timestamp) ");
		sql.append("and com.id IN (:id) ) as December ");
		sql.append("FROM tb_m_status tms ORDER BY tms.name");

		List<Object[]> listData = em.createNativeQuery(sql.toString(), Object[].class).setParameter("id", data).getResultList();
		List<TicketCharts> listCharts = new ArrayList<>();

		listData.forEach(l -> {
			TicketCharts ticket = new TicketCharts();
			ticket.setName((String) l[0]);
			ticket.setJanuary((BigInteger) l[1]);
			ticket.setFebruary((BigInteger) l[2]);
			ticket.setMarch((BigInteger) l[3]);
			ticket.setApril((BigInteger) l[4]);
			ticket.setMay((BigInteger) l[5]);
			ticket.setJune((BigInteger) l[6]);
			ticket.setJuly((BigInteger) l[7]);
			ticket.setAugust((BigInteger) l[8]);
			ticket.setSeptember((BigInteger) l[9]);
			ticket.setOctober((BigInteger) l[10]);
			ticket.setNovember((BigInteger) l[11]);
			ticket.setDecember((BigInteger) l[12]);
			listCharts.add(ticket);
		});
		return listCharts;
	}

	@Override
	public List<?> getListRelations(String data) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("SELECT usr.name as agent, tkt.idCustomer.idCompany.name as company, ");
		query.append("tkt.idCustomer.name as customer, count(tkt.id) as total, ");
		query.append("count(sts.id) as open, count(sts2.id) as close, count(sts3.id) as reopen ");
		query.append("FROM Tickets tkt LEFT JOIN Status sts ON sts.id = tkt.idStatus.id AND sts.code = 'OP' ");
		query.append("LEFT JOIN Status sts2 ON sts2.id = tkt.idStatus.id AND sts2.code = 'CL' ");
		query.append("LEFT JOIN Status sts3 ON sts3.id = tkt.idStatus.id AND sts3.code = 'RO' ");
		query.append("LEFT JOIN AgentRelations ar ON ar.idCompany.id = tkt.idCustomer.idCompany.id ");
		query.append("LEFT JOIN Users usr ON usr.id = ar.idAgent.id ");
		query.append("WHERE tkt.idCustomer.idCompany.id = :id ");
		query.append("GROUP BY agent, company, customer ");
		List<Object[]> listData = em.createQuery(query.toString(), Object[].class).setParameter("id", data)
				.getResultList();

		return Hibernate.bMapperList(listData, "agent", "company", "customer", "total", "open", "close", "reopen");
	}

	@Override
	public Tickets update(Tickets data) throws Exception {
		return em.merge(data);
	}

}

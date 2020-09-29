package com.lawencon.booting.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_r_dtl_ticket")
public class TicketDetails extends BaseModel{

	@ManyToOne
	@JoinColumn(name = "id_ticket")
	private Tickets idTicket;
	
	private String sender;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	private String description;

	public Tickets getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Tickets idTicket) {
		this.idTicket = idTicket;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
}

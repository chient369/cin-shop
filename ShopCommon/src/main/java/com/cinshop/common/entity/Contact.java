package com.cinshop.common.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false)
	private String email;

	@Column(length = 4096, nullable = false)
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedTime;
	
	
	private boolean isReplied = false;

	public Contact() {

	}

	public Contact(String email, String content, Date receivedTime) {
		super();
		this.email = email;
		this.content = content;
		this.receivedTime = receivedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public boolean isReplied() {
		return isReplied;
	}

	public void setReplied(boolean isReplied) {
		this.isReplied = isReplied;
	}
	

}

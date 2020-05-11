package com.meritamerica.assignment6.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact_details", catalog = "merit_bank_database")
public class AccountHolderContactDetails {

	
	//JPA Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private Integer id;

	//@JoinColumn(name = "contact_id", referencedColumnName = "ah_id")
	@OneToOne(cascade = CascadeType.ALL)
	private AccountHolder holder;
	
	public AccountHolder getHolder() {
		return holder;
	}

	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}

	//@Column
	private String email;
	//@Column
	private String phoneNum;
	
	public AccountHolderContactDetails() {
	}

	public AccountHolderContactDetails(String email, String phoneNum) {
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	
	
}

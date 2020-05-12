package com.meritamerica.assignment6.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cd_offerings")//, catalog = "merit_bank_database")
public class CDOffering {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CDAccount> cdAccounts;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "cdo_id")
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	//@NotNull
	//@Column
	private int term;
	////@NotNull
	//@Column
	private double interestRate=0.03;

	/**
	 * 
	 * @param term refers to the number of years
	 */
	public void setTerm(int term) {
		this.term = term;
	}
	/**
	 * 
	 * @param interestRate the interest of that year plan
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * 
	 * Constructor for creating CDOfferings
	 * 
	 * @param term years  of this CDOffering
	 * @param interestRate  interestRate for this CDOffering
	 */
	public CDOffering(int term, double interestRate){
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public CDOffering() {
	}
	
	
	
	//public CDOffering() {
		//this.term = 0;
		//this.interestRate = 0;
	//}

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}
	public void setCdAccounts(List<CDAccount> cdAccounts) {
		this.cdAccounts = cdAccounts;
	}
	/**
	 * 
	 * @return term in years of this CDOffering
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * 
	 * @return interestRare of this CDOffering
	 */
	public double getInterestRate() {
		return interestRate;
	}
	/**
	 * 
	 * @param string refers to save data
	 * @return returns updated CDOffering class
	 */
	public static CDOffering readFromString(String string) {
		//3,0.019
		int term = Integer.parseInt(string.split(",")[0]);
		double ir = Double.parseDouble(string.split(",")[1]);

		CDOffering cd = new CDOffering(term, ir);
		return cd;
	}
	/**
	 * 
	 * @return the data in a string to write on save data for record
	 */
	public String writeToString() {
		//3,0.019
		String term = getTerm()+"";
		String ir = getInterestRate()+"";
		return term+","+ir;
	}
}


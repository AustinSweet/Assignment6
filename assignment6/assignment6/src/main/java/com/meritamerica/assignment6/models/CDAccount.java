package com.meritamerica.assignment6.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CDAccount extends BankAccount{
	
	@ManyToOne
	private AccountHolder holder;
	
	@ManyToOne
	//@JoinColumn(name = "cdo_id")
	CDOffering offering;
	
	public CDOffering getOffering() {
		return offering;
	}
	public void setOffering(CDOffering offering) {
		this.offering = offering;
	}
	
	public CDAccount() {
	}
	
	/**
	 * 
	 * @param offering determines the starting balance and interest rate
	 * @param balance determines current balance
	 */
	public CDAccount (CDOffering offering, double balance) {
		super(offering, balance);
		this.offering = offering;
		this.balance = balance;
	}
	
	/**
	 * 
	 * @return returns the interest rate for the CD account
	 */
	public double getInterestRate() {
		return offering.getInterestRate();
	}

	/**
	 * 
	 * @param interestRate
	 */
	public void setInterestRate(double interestRate) {
		this.offering.setInterestRate(interestRate);
	}
	
	/**
	 * 
	 * @param term
	 */
	public void setTerm(int term) {
		this.offering.setTerm(term);
	}
	
	/**
	 * 
	 * @return returns the number of years for this CD account
	 */
	public int getTerm() {
		return offering.getTerm();
	}
	
	
	public AccountHolder getHolder() {
		return holder;
	}
	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}
	/**
	 * 
	 * @return returns the possible future value
	 */
	public double futureValue() {
		return (balance * (1+futureValue()*getInterestRate()*getTerm()));
	}
	/**
	 * 	
	 * @return false because at this time the CDAccount cannot withdraw
	 */	
		public boolean withdraw(double amount) {
			return false;
		}
		
	/**
	 * 	
	 * @return false because at this time the CDAccount cannot deposit
	 */
		public boolean deposit(double amount) {
			return false;	
		}
	
	/**
	 * 
	 * @param string refers to data on the text file
	 * @return returns the updated CDAccount class
	 */
	public static CDAccount readFromString(String string) {
		//84,20000,0.03,01/03/2020,5
		long accountNum = Integer.parseInt(string.split(",")[0]);
		double balance = Double.parseDouble(string.split(",")[1]);
		double ir = Double.parseDouble(string.split(",")[2]);
		String sDate = string.split(",")[3];
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int term = Integer.parseInt(string.split(",")[4]);
		
		
		CDOffering offering = new CDOffering(term, ir);
		offering.setTerm(term);
		CDAccount cd = new CDAccount(offering, ir);
		cd.setAccountNumber(accountNum);
		cd.setBalance(balance);
		cd.setINTEREST_RATE(ir);
		cd.setDate(date);
		return cd;
	}
	
	/**
	 * 
	 * @param date refers to the date the account is opened
	 * @return returns starting date
	 */
	public Date getOpenedOn(Date date) {
		return date;
	}
}

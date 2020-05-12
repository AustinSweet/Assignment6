package com.meritamerica.assignment6.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.models.MeritBank;
import com.meritamerica.assignment6.models.SavingsAccount;
import com.meritamerica.assignment6.repos.AccountHolderRepo;
import com.meritamerica.assignment6.repos.CDAccountRepo;
import com.meritamerica.assignment6.repos.CDOfferingRepo;
import com.meritamerica.assignment6.repos.CheckingAccountRepo;
import com.meritamerica.assignment6.repos.AccountHolderContactDetailsRepo;
import com.meritamerica.assignment6.repos.SavingsAccountRepo;

@RestController
public class MeritBankController {

	//TODO create Status Code Returns for our exceptions
	//TODO implement methods inside of get/post methods on the logger object we create
	//TODO ditch autowired, implement h2, change Auto to Identity, maybe change id type from Integers to Long (This shouldn't matter though)
	
	//When you connect to the H2 url below, use the same h2 url to connect. It will default to a test value
	
	//H2 Web Url:
	//http://localhost:8080/h2-console/login.jsp
	
	//Info for application.properties file:
	//spring.datasource.url = jdbc:h2:mem:testdb
	//spring.datasource.driverClassName = org.h2.Driver
	//spring.datasource.username = sa
	//spring.datasource.password = 
	
	Logger logger = LoggerFactory.getLogger(MeritBankController.class);

	List<AccountHolder> aHController = new ArrayList<AccountHolder>();
	List<CDOffering> cDController = new ArrayList<CDOffering>();
	
	//@Autowired
	private AccountHolderRepo holderRepo;
	//@Autowired
	private AccountHolderContactDetailsRepo contactRepo;
	//@Autowired
	private CheckingAccountRepo checkingRepo;
	//@Autowired
	private SavingsAccountRepo savingsRepo;
	//@Autowired
	private CDAccountRepo cdARepo;
	//@Autowired
	private CDOfferingRepo cdORepo;
	
	public MeritBankController() {
	}

	@PostMapping(value = "/AccountHolders")
	@Valid
	public ResponseEntity<AccountHolder> addAccountHolderFromReq(@Valid @RequestBody AccountHolder data) {
		if (data.getFirstName() == null || data.getMiddleName() == null || data.getLastName() == null || data.getSSN() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			aHController.add(data);
			return new ResponseEntity<>(data, HttpStatus.CREATED);
		}
	}

	@GetMapping(value = "/AccountHolders")
	public ResponseEntity<List<AccountHolder>> getAccountHoldersFromReq() {
		return new ResponseEntity<>(this.aHController, HttpStatus.OK);
	}

	@GetMapping(value = "/AccountHolders/{id}")
	public ResponseEntity<AccountHolder> getAccountHolderByID (@PathVariable int id) {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(aHController.get(id - 1), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@Valid
	public ResponseEntity<CheckingAccount> addCheckingAccountByReq (@Valid @PathVariable int id,@Valid @RequestBody CheckingAccount data) throws ExceedsCombinedBalanceLimitException {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else if (data.getBalance() < 0 || data.getBalance() > 250000) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			aHController.get(id - 1).addCheckingAccount(data);
			return new ResponseEntity<>(data, HttpStatus.CREATED);
		}
	}

	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public ResponseEntity<CheckingAccount[]> getCheckingAccountsById (@PathVariable int id) {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(aHController.get(id - 1).getCheckingAccounts(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@Valid
	public ResponseEntity<SavingsAccount> addSavingsAccountByReq (@PathVariable int id,@Valid @RequestBody SavingsAccount data) throws ExceedsCombinedBalanceLimitException {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else if (data.getBalance() < 0 || data.getBalance() > 250000) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		aHController.get(id - 1).addSavingsAccount(data);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public ResponseEntity<SavingsAccount[]> getSavingsAccountsById (@PathVariable int id) {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(aHController.get(id - 1).getSavingsAccounts(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@Valid
	public ResponseEntity<CDAccount>addCDAccountByReq (@PathVariable int id,@Valid @RequestBody CDAccount data) {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else if (data.getBalance() < 0 || data.getBalance() > 250000) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		aHController.get(id - 1).addCDAccount(data);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public ResponseEntity<CDAccount[]> getCDAccountByReq (@PathVariable int id) {
		if (aHController.get(id - 1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(aHController.get(id - 1).getCDAccounts(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/CDOfferings")
	@Valid
	public CDOffering addCDOfferingByReq(@Valid @RequestBody CDOffering data) {
		cDController.add(data);
		return data;
	}

	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferingsByReq() {
		return this.cDController;
	}

}

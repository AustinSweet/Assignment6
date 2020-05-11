package com.meritamerica.assignment6.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CheckingAccount;

public interface CheckingAccountRepo extends JpaRepository<CheckingAccount, Integer>{
	List<CheckingAccount> findByAccountHolder(Integer iD);

}

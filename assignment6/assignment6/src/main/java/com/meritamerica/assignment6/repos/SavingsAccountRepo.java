package com.meritamerica.assignment6.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.SavingsAccount;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Integer>{
	List<SavingsAccount> findByAccountHolder(Integer iD);

}

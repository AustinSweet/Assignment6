package com.meritamerica.assignment6.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.AccountHolderContactDetails;
import com.meritamerica.assignment6.models.CDAccount;

public interface AccountHolderContactDetailsRepo extends JpaRepository<AccountHolderContactDetails, Integer>{
	List<AccountHolderContactDetails> findByAccountHolder(Integer iD);

}

package com.meritamerica.assignment6.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;

public interface CDOfferingRepo extends JpaRepository<CDOffering, Integer>{
	Optional<CDOffering> findById(Integer iD);

}

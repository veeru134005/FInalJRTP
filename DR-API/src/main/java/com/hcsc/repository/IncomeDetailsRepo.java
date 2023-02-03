package com.hcsc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcsc.entity.IncomeDetails;

public interface IncomeDetailsRepo extends JpaRepository<IncomeDetails, Serializable> {

	@Query("from IncomeDetails inc inner join inc.caseId as ca on inc.caseId=ca.caseId where ca.caseId=:caseId")
	public List<IncomeDetails> fetchIncomeByCaseId(@Param("caseId") Integer caseId);
}

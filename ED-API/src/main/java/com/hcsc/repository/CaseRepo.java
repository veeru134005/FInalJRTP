package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.DcCaseEntity;

public interface CaseRepo extends JpaRepository<DcCaseEntity, Serializable>{

	public DcCaseEntity findByCaseNum(Long caseNum);
	
}

package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.DcIncomeEntity;

public interface IncomeDetailsRepo extends JpaRepository<DcIncomeEntity, Serializable> {


	public DcIncomeEntity findByCaseNum(Long caseId);
}

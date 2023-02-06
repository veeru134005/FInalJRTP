package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.DcEducationEntity;

public interface EducationRepo extends JpaRepository<DcEducationEntity, Serializable>{

	public DcEducationEntity findByCaseNum(Long caseId);
}

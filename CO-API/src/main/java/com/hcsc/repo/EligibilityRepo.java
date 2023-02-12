package com.hcsc.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.EligibilityEntity;

public interface EligibilityRepo extends JpaRepository<EligibilityEntity, Serializable> {

	public EligibilityEntity findByCaseNum(Long caseNum);

}

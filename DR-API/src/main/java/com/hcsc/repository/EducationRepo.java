package com.hcsc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcsc.entity.EducationDetails;

public interface EducationRepo extends JpaRepository<EducationDetails, Serializable>{
	@Query("from EducationDetails as ed inner join ed.caseId as ca on ed.caseId.caseId=ca.caseId where ca.caseId=1")
	public List<EducationDetails> fetchEducationByCaseId(@Param("caseId") Integer caseId);
}

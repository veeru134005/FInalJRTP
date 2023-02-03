package com.hcsc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcsc.entity.EducationDetails;
import com.hcsc.entity.KidsDetails;

public interface KidsDetailsRepo extends JpaRepository<KidsDetails, Serializable> {

	@Query("select * from KidsDetails kid inner join kid.caseId as ca on kid.caseId=ca.caseId where ca.caseId=:caseId")
	public List<EducationDetails> fetchKidsByCaseId(@Param("caseId") Integer caseId);
}

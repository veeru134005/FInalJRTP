package com.hcsc.entity;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoUtility {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<EligibilityEntity> getEligibleMembers() {

		String sql = "SELECT * FROM ed_elg_dtl ed WHERE  end_date >= CURRENT_TIMESTAMP AND ed.case_num IN( SELECT case_Num FROM ed_trigegers WHERE case_Num=ed.case_num AND STATUS='Success')";

		return entityManager.createNativeQuery(sql, EligibilityEntity.class).getResultList();
	}
	
	
	public UserRegistration getUserData(Long caseNum){
		String sql="SELECT * FROM user_registration WHERE app_id=(SELECT app_id FROM dc_cases WHERE case_num='"+caseNum+"')";
		
		return (UserRegistration) entityManager.createNativeQuery(sql, UserRegistration.class).getSingleResult();
	}

}

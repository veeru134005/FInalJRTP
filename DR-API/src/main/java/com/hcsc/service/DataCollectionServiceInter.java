package com.hcsc.service;

import java.util.Map;

import com.hcsc.pojo.CaseData;
import com.hcsc.pojo.EducationData;
import com.hcsc.pojo.IncomeData;
import com.hcsc.pojo.KidsData;

public interface DataCollectionServiceInter {

	public String genRandomCaseIdSearchByUsrId(Integer appId);

	public Map<Integer, String> getPlans();

	public String savePlanSelection(CaseData casedata);
	
	public String saveIncomeDetails(IncomeData details);
	
	public String saveEducationDetails(EducationData education);
	
	public String saveKidsDetails(KidsData kitData);

	public Map<String,Object>  getsummary(Integer caseid);

}

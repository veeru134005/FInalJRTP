package com.hcsc.service;

import com.hcsc.pojo.Education;
import com.hcsc.pojo.Income;
import com.hcsc.pojo.KidsInfo;
import com.hcsc.pojo.PlanSelection;
import com.hcsc.pojo.Summary;

public interface DcService {

	public PlanSelection creatCase(Integer appId);

	public Long updateCitizenPlan(PlanSelection planSelection);

	public Long saveIncomeDetails(Income details);

	public Long saveEducationDetails(Education education);

	public Summary saveKidsDetails(KidsInfo kitData);

}

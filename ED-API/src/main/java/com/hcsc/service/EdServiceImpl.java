package com.hcsc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.entity.DcCaseEntity;
import com.hcsc.entity.DcEducationEntity;
import com.hcsc.entity.DcIncomeEntity;
import com.hcsc.entity.DcKidsEntity;
import com.hcsc.entity.EdTriggerEntity;
import com.hcsc.entity.EligibilityEntity;
import com.hcsc.entity.UserRegistration;
import com.hcsc.pojo.CitizenEligibility;
import com.hcsc.repository.CaseRepo;
import com.hcsc.repository.CitizenAppRepo;
import com.hcsc.repository.EdTriggerRepo;
import com.hcsc.repository.EducationRepo;
import com.hcsc.repository.EligibilityRepo;
import com.hcsc.repository.IncomeDetailsRepo;
import com.hcsc.repository.KidsDetailsRepo;
import com.hcsc.repository.PlansRepo;

@Service
public class EdServiceImpl implements EdServicce {

	@Autowired
	private CaseRepo caseRepo;

	@Autowired
	private PlansRepo plansRepo;

	@Autowired
	private KidsDetailsRepo kidRepo;

	@Autowired
	private IncomeDetailsRepo incomeDetailsRepo;
	@Autowired
	private CitizenAppRepo citizenAppRepo;
	
	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private EligibilityRepo eligibilityRepo;
	
	@Autowired
	private EdTriggerRepo edTriggerRepo;
	
	@Override
	public CitizenEligibility getEligibleData(Long caseNum) {

		DcCaseEntity caseDtl = caseRepo.findByCaseNum(caseNum);
		CitizenEligibility citiEl = new CitizenEligibility();

		if (caseDtl != null) {
			String planName = plansRepo.findByPlanId(caseDtl.getPlanId()).getPlanName();
			String accHoldName = citizenAppRepo.getAccHoldName(caseDtl.getAppId());
			citiEl.setHolderName(accHoldName);
			if (planName.equalsIgnoreCase("SNAP")) {
				citiEl = getSnapEligibility(caseNum, planName);
			} else if (planName.equalsIgnoreCase("CCAP")) {
				citiEl = getCcapEligibility(caseNum, planName);
			} else if (planName.equalsIgnoreCase("MEDICAID")) {

				citiEl = getMedicaidEligibility(caseNum, planName);
			} else if (planName.equalsIgnoreCase("MEDICARE")) {

				citiEl = getMediCareEligibility(caseNum, planName, caseDtl.getAppId());
			} else if (planName.equalsIgnoreCase("RIW")) {

				citiEl = getRiwEligibility(caseNum, planName, caseDtl.getAppId());
			}

		}

		if (citiEl.isCitigenEligible()) {

			EligibilityEntity elEntity = new EligibilityEntity();
			EdTriggerEntity edTriggerEntity = new EdTriggerEntity();

			BeanUtils.copyProperties(citiEl, elEntity);

			EligibilityEntity res = eligibilityRepo.save(elEntity);

			edTriggerEntity.setCaseNum(res.getCaseNum());
			edTriggerEntity.setStatus("Pending");
			edTriggerRepo.save(edTriggerEntity);

		}

		return citiEl;
	}


	private CitizenEligibility getSnapEligibility(Long caseNum, String planName) {

		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		CitizenEligibility cte = new CitizenEligibility(1, "SNAP", "DENIED", 0d, caseNum, "NA", "", "", "",false);
		cte.setStartDate(cte.getStartDate());
		cte.setEndDate(cte.getEndDate());

		if (income != null
				&& (income.getSalaryIncome() + income.getPropertyIncome() + income.getPropertyIncome()) <= 3000) {
			cte.setCaseNum(caseNum);
			cte.setPlanName(planName);
			cte.setStatus("Approved");
			cte.setBeniftAmout(3000d);
			cte.setDeniedReason("NA");
			cte.setCitigenEligible(true);

		}
		return cte;
	}

	private CitizenEligibility getCcapEligibility(Long caseNum, String planName) {

		CitizenEligibility cte = new CitizenEligibility(1, "CCAP", "DENIED", 0d, caseNum, "Income Eigibility Issue", "", "", "",false);

		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		Optional<DcKidsEntity> kidEl = kidRepo.findByCaseNum(caseNum).stream().filter(k -> k.getKidAge() > 16)
				.findFirst();
		
		if (income != null && ((income.getSalaryIncome() + income.getPropertyIncome() + income.getPropertyIncome()) <= 3000)) {
			cte.setCaseNum(caseNum);
			cte.setPlanName(planName);
			cte.setStatus("Approved");
			cte.setBeniftAmout(3000d);
			cte.setDeniedReason("NA");
			cte.setCitigenEligible(true);
		}
		if (!kidEl.isPresent()) {
			cte.setDeniedReason("Kid age EligibilityIssue");
			cte.setStatus("DENIED");
			cte.setCitigenEligible(false);
		}
		return cte;
	}
	
	private CitizenEligibility getMedicaidEligibility(Long caseNum, String planName) {
		CitizenEligibility cte = new CitizenEligibility(1, "MEDICAID", "DENIED", 0d, caseNum, "Income Eigibility Issue","", "", "",false);

		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		if (income != null && income.getSalaryIncome() <= 3000 && income.getPropertyIncome() == 0 && income.getRentIncome() == 0) {
			cte.setCaseNum(caseNum);
			cte.setPlanName(planName);
			cte.setStatus("Approved");
			cte.setBeniftAmout(3000d);
			cte.setDeniedReason("NA");
			cte.setCitigenEligible(true);
		}
		return cte;
	}
	
	private CitizenEligibility getMediCareEligibility(Long caseNum, String planName, Integer appId) {

		CitizenEligibility cte = new CitizenEligibility(1, "MEDICAID", "DENIED", 0d, caseNum, "Age Eigibility Issue","", "", "",false);

		UserRegistration citizenData = citizenAppRepo.findByAppId(appId);

		if (citizenData != null && (cte.getCitiZenAge(citizenData.getDob()) >= 65)) {
			cte.setCaseNum(caseNum);
			cte.setPlanName(planName);
			cte.setStatus("Approved");
			cte.setBeniftAmout(3000d);
			cte.setDeniedReason("NA");
			cte.setCitigenEligible(true);
		}
		return cte;
	}
	
	private CitizenEligibility getRiwEligibility(Long caseNum, String planName, Integer appId) {
		
		CitizenEligibility cte = new CitizenEligibility(1, "MEDICAID", "DENIED", 0d, caseNum, "Age Eigibility Issue","", "", "",false);
		
		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);
		DcEducationEntity education = educationRepo.findByCaseNum(caseNum);
		
		if(income!=null && income.getSalaryIncome()==0 && education!=null) {
			cte.setCaseNum(caseNum);
			cte.setPlanName(planName);
			cte.setStatus("Approved");
			cte.setBeniftAmout(3000d);
			cte.setDeniedReason("NA");
			cte.setCitigenEligible(true);
		}
		
		return cte;
	}

}

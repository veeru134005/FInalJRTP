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

		CitizenEligibility cte = null;

		if (caseDtl != null) {
			String planName = plansRepo.findByPlanId(caseDtl.getPlanId()).getPlanName();
			String accHoldName = citizenAppRepo.getAccHoldName(caseDtl.getAppId());

			cte = new CitizenEligibility(1, planName, "DENIED", 0d, caseNum, "Age Eigibility Issue", accHoldName, "",
					"", false, caseDtl.getAppId());

			if (planName.equalsIgnoreCase("SNAP")) {
				getSnapEligibility(cte);
			} else if (planName.equalsIgnoreCase("CCAP")) {
				getCcapEligibility(cte);
			} else if (planName.equalsIgnoreCase("MEDICAID")) {

				getMedicaidEligibility(cte);
			} else if (planName.equalsIgnoreCase("MEDICARE")) {
				getMediCareEligibility(cte);
			} else if (planName.equalsIgnoreCase("RIW")) {
				getRiwEligibility(cte);
			}

		}

		if (cte.isCitigenEligible()) {

			cte.setStatus("Approved");
			cte.setDeniedReason("NA");
			cte.setStartDate(cte.getStartDate());
			cte.setEndDate(cte.getEndDate());

			EligibilityEntity elEntity = new EligibilityEntity();
			EdTriggerEntity edTriggerEntity = new EdTriggerEntity();

			BeanUtils.copyProperties(cte, elEntity);

			EligibilityEntity res = eligibilityRepo.save(elEntity);

			edTriggerEntity.setCaseNum(res.getCaseNum());
			edTriggerEntity.setStatus("Pending");
			edTriggerRepo.save(edTriggerEntity);

		}

		return cte;
	}

	private CitizenEligibility getSnapEligibility(CitizenEligibility elb) {

		Long caseNum = elb.getCaseNum();
		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		if (income != null
				&& (income.getSalaryIncome() + income.getPropertyIncome() + income.getPropertyIncome()) <= 3000) {
			elb.setBeniftAmout(3000d);
			elb.setCitigenEligible(true);

		} else {
			elb.setDeniedReason("Income is more then eligible Amount");
		}
		return elb;
	}

	private void getCcapEligibility(CitizenEligibility elb) {

		Long caseNum = elb.getCaseNum();
		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		Optional<DcKidsEntity> kidEl = kidRepo.findByCaseNum(caseNum).stream().filter(k -> k.getKidAge() > 16)
				.findFirst();

		if (income == null) {
			elb.setDeniedReason("Income is below eligible Amount");
		}

		if (income != null
				&& ((income.getSalaryIncome() + income.getPropertyIncome() + income.getPropertyIncome()) <= 3000)) {
			elb.setBeniftAmout(3000d);
			elb.setCitigenEligible(true);
		}
		if (!kidEl.isPresent()) {
			elb.setDeniedReason("Kid age EligibilityIssue");
			elb.setStatus("DENIED");
			elb.setCitigenEligible(false);
		}
	}

	private void getMedicaidEligibility(CitizenEligibility elb) {

		Long caseNum = elb.getCaseNum();

		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);

		if (income.getSalaryIncome() <= 3000 && income.getPropertyIncome() == 0 && income.getRentIncome() == 0) {
			elb.setDeniedReason("Income is more then eligible Amount");
		}

		if (income != null && income.getSalaryIncome() <= 3000 && income.getPropertyIncome() == 0
				&& income.getRentIncome() == 0) {
			elb.setBeniftAmout(3000d);
			elb.setDeniedReason("NA");
			elb.setCitigenEligible(true);
		}
	}

	private void getMediCareEligibility(CitizenEligibility elb) {

		Integer appId = elb.getAppId();

		UserRegistration citizenData = citizenAppRepo.findByAppId(appId);

		if (citizenData != null && (elb.getCitiZenAge(citizenData.getDob()) >= 65)) {
			elb.setBeniftAmout(3000d);
			elb.setDeniedReason("NA");
			elb.setCitigenEligible(true);
		} else {
			elb.setDeniedReason("Eligible Age must be above 65 Years");
		}
	}

	private void getRiwEligibility(CitizenEligibility elb) {

		Long caseNum = elb.getCaseNum();

		DcIncomeEntity income = incomeDetailsRepo.findByCaseNum(caseNum);
		DcEducationEntity education = educationRepo.findByCaseNum(caseNum);

		if (income != null && income.getSalaryIncome() == 0 && education != null) {
			elb.setBeniftAmout(3000d);
			elb.setDeniedReason("NA");
			elb.setCitigenEligible(true);
		} else {
			elb.setDeniedReason("Eligible only for Unemploye)");
		}

	}

}
package com.hcsc.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.entity.DcCaseEntity;
import com.hcsc.entity.DcEducationEntity;
import com.hcsc.entity.DcIncomeEntity;
import com.hcsc.entity.UserRegistration;
import com.hcsc.pojo.Education;
import com.hcsc.pojo.Income;
import com.hcsc.pojo.KidsInfo;
import com.hcsc.pojo.PlanSelection;
import com.hcsc.pojo.Summary;
import com.hcsc.repository.CaseRepo;
import com.hcsc.repository.CitizenAppRepo;
import com.hcsc.repository.EducationRepo;
import com.hcsc.repository.IncomeDetailsRepo;
import com.hcsc.repository.KidsDetailsRepo;
import com.hcsc.repository.PlansRepo;

@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private EducationRepo educationRepo;

	@Autowired
	private KidsDetailsRepo kidsRepo;

	@Autowired
	private CaseRepo caseRepo;

	@Autowired
	private PlansRepo plansRepo;

	@Autowired
	private IncomeDetailsRepo incomeDetailsRepo;

	@Autowired
	private CitizenAppRepo citizenAppRepo;

	@Override
	public PlanSelection creatCase(Integer appId) {

		Optional<UserRegistration> citizen = citizenAppRepo.findById(appId);
		PlanSelection planSelection = new PlanSelection();
		if (citizen.isPresent()) {
			planSelection.setCaseNum((long) Math.floor(Math.random() * (10000 - 100 + 1) + 100));
			planSelection.setPlansInfo(
					plansRepo.findAll().stream().collect(Collectors.toMap(i -> i.getPlanId(), i -> i.getPlanName())));
			planSelection.setStatus("Succcess");
			return planSelection;
		}

		planSelection.setStatus("Failure");

		return planSelection;
	}

	@Override
	public Long updateCitizenPlan(PlanSelection planSelection) {

		DcCaseEntity dcCase = new DcCaseEntity();
		BeanUtils.copyProperties(planSelection, dcCase);

		DcCaseEntity dcData = caseRepo.save(dcCase);

		return dcData.getCaseNum();
	}

	@Override
	public Long saveIncomeDetails(Income incDetails) {

		DcIncomeEntity dcIncomeEntity = new DcIncomeEntity();

		BeanUtils.copyProperties(incDetails, dcIncomeEntity);

		return incomeDetailsRepo.save(dcIncomeEntity).getCaseNum();
	}

	@Override
	public Long saveEducationDetails(Education education) {

		DcEducationEntity dcEducationEntity = new DcEducationEntity();

		BeanUtils.copyProperties(education, dcEducationEntity);

		return educationRepo.save(dcEducationEntity).getCaseNum();
	}

	@Override
	public Summary saveKidsDetails(KidsInfo kidData) {

		Long caseNum = kidData.getCaseNum();
		Summary summary = new Summary();
		
		kidData.getKidsInfo().stream().peek(k->k.setCaseNum(caseNum)).forEach(kidsRepo::save);

		summary.setEducationDetails(educationRepo.findByCaseNum(caseNum));

		summary.setIncome(incomeDetailsRepo.findByCaseNum(caseNum));

		summary.setKidsEntity(kidsRepo.findByCaseNum(caseNum));

		return summary;
	}

}

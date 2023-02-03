package com.hcsc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.entity.Case;
import com.hcsc.entity.EducationDetails;
import com.hcsc.entity.IncomeDetails;
import com.hcsc.entity.KidsDetails;
import com.hcsc.pojo.CaseData;
import com.hcsc.pojo.EducationData;
import com.hcsc.pojo.IncomeData;
import com.hcsc.pojo.KidsData;
import com.hcsc.repository.CaseRepo;
import com.hcsc.repository.EducationRepo;
import com.hcsc.repository.IncomeDetailsRepo;
import com.hcsc.repository.KidsDetailsRepo;
import com.hcsc.repository.PlansRepo;

@Service
public class DataCollectionServiceImpl implements DataCollectionServiceInter {

	@Autowired
	private ARfeignClient arFeignClient;

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

	@Override
	public String genRandomCaseIdSearchByUsrId(Integer appId) {

		if (arFeignClient.isValideCitizen(appId) != null) {
			return String.valueOf((int) Math.floor(Math.random() * (10000 - 100 + 1) + 100));
		}
		return "Invalide user Application Id";
	}

	@Override
	public Map<Integer, String> getPlans() {

		return plansRepo.findAll().stream().collect(Collectors.toMap(i -> i.getPlanId(), i -> i.getPlanName()));
	}

	@Override
	public String savePlanSelection(CaseData data) {
		Case caseData = new Case();
		BeanUtils.copyProperties(caseData, data);
		caseRepo.save(caseData);

		return "User Plan Data is saved";
	}

	@Override
	public String saveIncomeDetails(IncomeData details) {

		IncomeDetails inc = new IncomeDetails();

		BeanUtils.copyProperties(details, inc);

		inc.setCaseId(caseRepo.findById(details.getCaseId()).get());

		incomeDetailsRepo.save(inc);

		return "Income Details Submitted Succeffuly";
	}

	@Override
	public String saveEducationDetails(EducationData education) {

		EducationDetails edu = new EducationDetails();

		BeanUtils.copyProperties(education, edu);

		edu.setCaseId(caseRepo.findById(education.getCaseId()).get());

		educationRepo.save(edu);

		return "Education Details Submmited succeffuly";
	}

	@Override
	public String saveKidsDetails(KidsData kitDetails) {

		KidsDetails kid = new KidsDetails();

		BeanUtils.copyProperties(kitDetails, kid);

		kid.setCaseId(caseRepo.findById(kitDetails.getCaseId()).get());

		kidsRepo.save(kid);

		return "Kids Details Submmited succeffuly";
	}

	@Override
	public Map<String, Object> getsummary(Case caseData) {

		Map<String, Object> summaryMap = new HashMap<>();

		summaryMap.put("Income", incomeDetailsRepo.fetchIncomeByCaseId(caseData.getCaseId()));
		summaryMap.put("Education", educationRepo.fetchEducationByCaseId(caseData.getCaseId()));
		summaryMap.put("Kids", kidsRepo.fetchKidsByCaseId(caseData.getCaseId()));

		return summaryMap;
	}

}
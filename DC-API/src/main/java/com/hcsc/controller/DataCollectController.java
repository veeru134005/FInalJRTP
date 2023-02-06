package com.hcsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.pojo.Education;
import com.hcsc.pojo.Income;
import com.hcsc.pojo.KidsInfo;
import com.hcsc.pojo.PlanSelection;
import com.hcsc.pojo.Summary;
import com.hcsc.service.DcService;


@RestController
public class DataCollectController {

	@Autowired
	private DcService dataService;

	@GetMapping("/create/{appId}")
	public PlanSelection createCase(@PathVariable Integer appId) {

		return dataService.creatCase(appId);
	}

	@PostMapping("/update")
	public Long updateCitizenPlan(@RequestBody PlanSelection planSelection) {
		return dataService.updateCitizenPlan(planSelection);
	}

	@PostMapping("/income")
	public Long saveIncomeDetails(@RequestBody Income details) {
		return dataService.saveIncomeDetails(details);
	}

	@PostMapping("/education")
	public Long saveEducationDetails(Education education) {
		return dataService.saveEducationDetails(education);
	}

	@PostMapping("/kid")
	public Summary saveKidsDetails(KidsInfo kitData) {
		return dataService.saveKidsDetails(kitData);
	}

}

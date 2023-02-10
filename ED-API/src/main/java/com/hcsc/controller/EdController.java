package com.hcsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.pojo.CitizenEligibility;
import com.hcsc.service.EdServicce;

@RestController
public class EdController {

	@Autowired
	private EdServicce edServicce;

	@GetMapping("/eligible/{casenum}")
	private CitizenEligibility checkEligibility(@PathVariable Long casenum) {

		return edServicce.checkEligibility(casenum);
	}

}
 